package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestResponse;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GetTeamChangeRequestServiceTest {
    private GetTeamChangeRequestService getTeamChangeRequestService;
    private LoadTeamChangeRequestPort loadTeamChangeRequestPortMock;

    @BeforeEach
    void setUp() {
        loadTeamChangeRequestPortMock = mock(LoadTeamChangeRequestPort.class);
        getTeamChangeRequestService = new GetTeamChangeRequestService(loadTeamChangeRequestPortMock);
    }

    @Test
    void testGetTeamChangeRequest_Success() {
        // Mock loadTeamChangeRequestPort
        TeamChangeRequest teamChangeRequest = new TeamChangeRequest("requestId", "developerId", "requestedProjectId", TeamRequestStatus.PENDING, LocalDate.now());
        when(loadTeamChangeRequestPortMock.loadTeamChangeRequest(any(LoadTeamChangeRequestCommand.class))).thenReturn(teamChangeRequest);

        // Get a team change request
        GetTeamChangeRequestCommand command = new GetTeamChangeRequestCommand("requestId");
        GetTeamChangeRequestResponse.Response response = getTeamChangeRequestService.getTeamChangeRequest(command);

        // Verify the response and method calls
        assertTrue(response instanceof GetTeamChangeRequestResponse.SuccessResponse);
        assertEquals(teamChangeRequest, ((GetTeamChangeRequestResponse.SuccessResponse) response).request());

        ArgumentCaptor<LoadTeamChangeRequestCommand> loadCommandCaptor = ArgumentCaptor.forClass(LoadTeamChangeRequestCommand.class);
        verify(loadTeamChangeRequestPortMock).loadTeamChangeRequest(loadCommandCaptor.capture());
        LoadTeamChangeRequestCommand loadCommand = loadCommandCaptor.getValue();
        assertEquals("requestId", loadCommand.getRequestId());
    }

    @Test
    void testGetTeamChangeRequest_Error() {
        // Mock loadTeamChangeRequestPort to throw an exception
        when(loadTeamChangeRequestPortMock.loadTeamChangeRequest(any(LoadTeamChangeRequestCommand.class)))
                .thenThrow(new IllegalArgumentException("Invalid team change request ID"));

        // Get a team change request
        GetTeamChangeRequestCommand command = new GetTeamChangeRequestCommand("invalidRequestId");
        GetTeamChangeRequestResponse.Response response = getTeamChangeRequestService.getTeamChangeRequest(command);

        // Verify the response
        assertTrue(response instanceof GetTeamChangeRequestResponse.ErrorResponse);
        assertEquals("Invalid team change request ID", ((GetTeamChangeRequestResponse.ErrorResponse) response).errorMessage());

        // Ensure loadTeamChangeRequestPort was called with the correct command
        ArgumentCaptor<LoadTeamChangeRequestCommand> loadCommandCaptor = ArgumentCaptor.forClass(LoadTeamChangeRequestCommand.class);
        verify(loadTeamChangeRequestPortMock).loadTeamChangeRequest(loadCommandCaptor.capture());
        LoadTeamChangeRequestCommand loadCommand = loadCommandCaptor.getValue();
        assertEquals("invalidRequestId", loadCommand.getRequestId());
    }
}
