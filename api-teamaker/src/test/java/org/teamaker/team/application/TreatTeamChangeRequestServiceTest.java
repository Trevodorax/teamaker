package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase.TreatTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase.TreatTeamChangeRequestResponse;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestPort;
import org.teamaker.team.domain.Team;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;
import org.teamaker.team.domain.TreatTeamStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreatTeamChangeRequestServiceTest {
    private TreatTeamChangeRequestService treatTeamChangeRequestService;
    private LoadTeamChangeRequestPort loadTeamChangeRequestPortMock;
    private LoadProjectPort loadProjectPortMock;
    private LoadDeveloperPort loadDeveloperPortMock;
    private SaveTeamChangeRequestPort saveTeamChangeRequestPortMock;
    private SaveTeamPort saveTeamPortMock;

    private static Team exampleTeam;
    private static Project exampleProject;
    private static Developer exampleDeveloper;

    @BeforeEach
    void setUp() {
        exampleTeam = new Team("projectId", new ArrayList<>(), false);
        exampleProject = new Project("projectId", "projectName", "description", ProjectPriority.NORMAL, ProjectStatus.PENDING, LocalDate.of(2023, 6, 6), LocalDate.of(2023, 6, 12), exampleTeam, Map.of());
        exampleDeveloper = new Developer("developerId", "John Doe", "john@example.com", LocalDate.of(2018, 6, 6));

        loadTeamChangeRequestPortMock = mock(LoadTeamChangeRequestPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        saveTeamChangeRequestPortMock = mock(SaveTeamChangeRequestPort.class);
        saveTeamPortMock = mock(SaveTeamPort.class);

        treatTeamChangeRequestService = new TreatTeamChangeRequestService(
                loadTeamChangeRequestPortMock,
                loadProjectPortMock,
                loadDeveloperPortMock,
                saveTeamChangeRequestPortMock,
                saveTeamPortMock
        );
    }

    @Test
    void testTreatTeamChangeRequest_Success() {
        // Mock load operations
        TeamChangeRequest realChangeRequest = new TeamChangeRequest("id", "developerId", "fromProjectId", "toProjectId", TeamRequestStatus.PENDING, LocalDate.of(2023, 6, 6));
        TeamChangeRequest teamChangeRequest = spy(realChangeRequest);
        when(teamChangeRequest.treat(TreatTeamStatus.APPROVED, exampleProject, exampleProject, exampleDeveloper)).thenReturn(null);
        when(loadTeamChangeRequestPortMock.loadTeamChangeRequest(any(LoadTeamChangeRequestCommand.class))).thenReturn(teamChangeRequest);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(exampleDeveloper);

        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(exampleProject);

        when(saveTeamChangeRequestPortMock.saveTeamChangeRequest(any(TeamChangeRequest.class))).thenReturn(teamChangeRequest); // Mock saveTeamChangeRequest to return the same instance
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(new Team("projectId", new ArrayList<>(), false));

        // Treat the team change request
        TreatTeamChangeRequestCommand command = new TreatTeamChangeRequestCommand("requestId", TreatTeamStatus.APPROVED);
        TreatTeamChangeRequestResponse.Response response = treatTeamChangeRequestService.treatTeamChangeRequest(command);

        // Verify the response and that save methods were called
        assertTrue(response instanceof TreatTeamChangeRequestResponse.SuccessResponse);

        // Verify that saveTeamChangeRequest and saveTeam were called with the expected arguments
        verify(saveTeamChangeRequestPortMock).saveTeamChangeRequest(teamChangeRequest);
        verify(saveTeamPortMock, times(2)).saveTeam(exampleProject.getTeam());
    }

    @Test
    void testTreatTeamChangeRequest_Error() {
        // Mock load operations
        TeamChangeRequest realChangeRequest = new TeamChangeRequest("id", "developerId", "fromProjectId", "toProjectId", TeamRequestStatus.PENDING, LocalDate.of(2023, 6, 6));
        TeamChangeRequest teamChangeRequest = spy(realChangeRequest);
        when(teamChangeRequest.treat(TreatTeamStatus.APPROVED, exampleProject, exampleProject, exampleDeveloper)).thenReturn(List.of("Error 1"));
        when(loadTeamChangeRequestPortMock.loadTeamChangeRequest(any(LoadTeamChangeRequestCommand.class))).thenReturn(teamChangeRequest);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(exampleDeveloper);

        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(exampleProject);

        // Mock save operations to return errors
        List<String> errors = new ArrayList<>();
        errors.add("Error 1");
        when(saveTeamChangeRequestPortMock.saveTeamChangeRequest(any(TeamChangeRequest.class))).thenReturn(null);
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(new Team("projectId", new ArrayList<>(), false));

        // Treat the team change request
        TreatTeamChangeRequestCommand command = new TreatTeamChangeRequestCommand("requestId", TreatTeamStatus.APPROVED);
        TreatTeamChangeRequestResponse.Response response = treatTeamChangeRequestService.treatTeamChangeRequest(command);

        // Verify the response
        assertTrue(response instanceof TreatTeamChangeRequestResponse.MultipleErrorsResponse);
        assertEquals(((TreatTeamChangeRequestResponse.MultipleErrorsResponse) response).errorMessages(), errors);
    }
}
