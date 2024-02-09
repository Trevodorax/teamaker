package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestResponse;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestPort;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsCommand;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsPort;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SubmitTeamChangeRequestServiceTest {
    private SubmitTeamChangeRequestService submitTeamChangeRequestService;
    private LoadDeveloperTeamChangeRequestsPort loadDeveloperTeamChangeRequestsPortMock;
    private CreateTeamChangeRequestPort createTeamChangeRequestPortMock;

    @BeforeEach
    void setUp() {
        loadDeveloperTeamChangeRequestsPortMock = mock(LoadDeveloperTeamChangeRequestsPort.class);
        createTeamChangeRequestPortMock = mock(CreateTeamChangeRequestPort.class);

        submitTeamChangeRequestService = new SubmitTeamChangeRequestService(
                loadDeveloperTeamChangeRequestsPortMock,
                createTeamChangeRequestPortMock
        );
    }

    @Test
    void testSubmitTeamChangeRequest_Success() {
        // Mock developer team change requests
        List<TeamChangeRequest> teamChangeRequests = new ArrayList<>();
        when(loadDeveloperTeamChangeRequestsPortMock.loadDeveloperTeamChangeRequests(any(LoadDeveloperTeamChangeRequestsCommand.class))).thenReturn(teamChangeRequests);

        // Mock createTeamChangeRequestPort
        TeamChangeRequest createdTeamChangeRequest = new TeamChangeRequest("requestId", "developerId", "fromProjectId", "toProjectId", TeamRequestStatus.PENDING, LocalDate.now());
        when(createTeamChangeRequestPortMock.createTeamChangeRequest(any(CreateTeamChangeRequestCommand.class))).thenReturn(createdTeamChangeRequest);

        // Submit a team change teamChangeRequest
        SubmitTeamChangeRequestCommand command = new SubmitTeamChangeRequestCommand("developerId", "requestedProjectId", "toProjectId");
        SubmitTeamChangeRequestResponse.Response response = submitTeamChangeRequestService.submitTeamChangeRequest(command);

        // Verify the response and method calls
        assertTrue(response instanceof SubmitTeamChangeRequestResponse.SuccessResponse);
        assertEquals(createdTeamChangeRequest, ((SubmitTeamChangeRequestResponse.SuccessResponse) response).teamChangeRequest());

        ArgumentCaptor<CreateTeamChangeRequestCommand> createCommandCaptor = ArgumentCaptor.forClass(CreateTeamChangeRequestCommand.class);
        verify(createTeamChangeRequestPortMock).createTeamChangeRequest(createCommandCaptor.capture());
        CreateTeamChangeRequestCommand createCommand = createCommandCaptor.getValue();
        assertEquals("developerId", createCommand.getDeveloperId());
        assertEquals("requestedProjectId", createCommand.getRequestedProjectId());
    }

    @Test
    void testSubmitTeamChangeRequest_RecentRequestExists() {
        // Mock developer team change requests with a recent teamChangeRequest
        List<TeamChangeRequest> teamChangeRequests = new ArrayList<>();
        teamChangeRequests.add(new TeamChangeRequest("requestId", "developerId", "fromProjectId", "toProjectId", TeamRequestStatus.PENDING, LocalDate.now().minusMonths(3)));
        when(loadDeveloperTeamChangeRequestsPortMock.loadDeveloperTeamChangeRequests(any(LoadDeveloperTeamChangeRequestsCommand.class))).thenReturn(teamChangeRequests);

        // Submit a team change teamChangeRequest
        SubmitTeamChangeRequestCommand command = new SubmitTeamChangeRequestCommand("developerId", "requestedProjectId", "toProjectId");
        SubmitTeamChangeRequestResponse.Response response = submitTeamChangeRequestService.submitTeamChangeRequest(command);

        // Verify the response
        assertTrue(response instanceof SubmitTeamChangeRequestResponse.ErrorResponse);
        assertEquals("Developer already asked to switch teams less than 6 months ago.", ((SubmitTeamChangeRequestResponse.ErrorResponse) response).errorMessage());

        // Ensure createTeamChangeRequestPort was not called
        verifyNoInteractions(createTeamChangeRequestPortMock);
    }
}
