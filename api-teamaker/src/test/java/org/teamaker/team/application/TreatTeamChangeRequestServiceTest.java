package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
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
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestCommand;
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
    private LoadDeveloperSkillsPort loadDeveloperSkillsPortMock;

    private static Team exampleTeam;
    private static Project exampleProject;
    private static Developer exampleDeveloper;

    @BeforeEach
    void setUp() {
        exampleTeam = new Team("projectId", new ArrayList<>(), false);
        exampleProject = new Project("projectId", "projectName", "description", ProjectPriority.NORMAL, ProjectStatus.PENDING, LocalDate.of(2023, 6, 6), LocalDate.of(2023, 9, 12), exampleTeam, Map.of());
        exampleDeveloper = new Developer("developerId", "John Doe", "john@example.com", LocalDate.of(2018, 6, 6), null);

        loadTeamChangeRequestPortMock = mock(LoadTeamChangeRequestPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        saveTeamChangeRequestPortMock = mock(SaveTeamChangeRequestPort.class);
        saveTeamPortMock = mock(SaveTeamPort.class);
        loadDeveloperSkillsPortMock = mock(LoadDeveloperSkillsPort.class);

        treatTeamChangeRequestService = new TreatTeamChangeRequestService(
                loadTeamChangeRequestPortMock,
                loadProjectPortMock,
                loadDeveloperPortMock,
                saveTeamChangeRequestPortMock,
                saveTeamPortMock,
                loadDeveloperSkillsPortMock
        );
    }

    @Test
    void testTreatTeamChangeRequest_Success() {
        // Mock load operations
        TeamChangeRequest realChangeRequest = new TeamChangeRequest("id", "developerId", "fromProjectId", "toProjectId", TeamRequestStatus.PENDING, LocalDate.of(2023, 6, 6));
        TeamChangeRequest teamChangeRequest = spy(realChangeRequest);
        doReturn(null).when(teamChangeRequest).treat(eq(TreatTeamStatus.APPROVED), any(Project.class), any(Project.class), any(Developer.class));
        when(loadTeamChangeRequestPortMock.loadTeamChangeRequest(any(LoadTeamChangeRequestCommand.class))).thenReturn(teamChangeRequest);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(exampleDeveloper);

        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(exampleProject);

        when(saveTeamChangeRequestPortMock.saveTeamChangeRequest(any(SaveTeamChangeRequestCommand.class))).thenReturn(teamChangeRequest); // Mock saveTeamChangeRequest to return the same instance
        when(saveTeamPortMock.saveTeam(any(SaveTeamCommand.class))).thenReturn(new Team("projectId", new ArrayList<>(), false));

        // Treat the team change request
        TreatTeamChangeRequestCommand command = new TreatTeamChangeRequestCommand("requestId", TreatTeamStatus.APPROVED);
        TreatTeamChangeRequestResponse.Response response = treatTeamChangeRequestService.treatTeamChangeRequest(command);

        // Verify the response and that save methods were called
        assertInstanceOf(TreatTeamChangeRequestResponse.SuccessResponse.class, response);

        // Verify that saveTeamChangeRequest and saveTeam were called with the expected arguments
        ArgumentCaptor<SaveTeamChangeRequestCommand> saveTeamChangeRequestCommandArgumentCaptor = ArgumentCaptor.forClass(SaveTeamChangeRequestCommand.class);
        verify(saveTeamChangeRequestPortMock).saveTeamChangeRequest(saveTeamChangeRequestCommandArgumentCaptor.capture());
        assertEquals(teamChangeRequest, saveTeamChangeRequestCommandArgumentCaptor.getValue().getTeamChangeRequest());
        verify(saveTeamPortMock, times(2)).saveTeam(any(SaveTeamCommand.class));
    }

    @Test
    void testTreatTeamChangeRequest_Error() {
        // Mock load operations
        TeamChangeRequest realChangeRequest = new TeamChangeRequest("id", "developerId", "fromProjectId", "toProjectId", TeamRequestStatus.PENDING, LocalDate.of(2023, 6, 6));
        TeamChangeRequest teamChangeRequest = spy(realChangeRequest);
        doReturn(List.of("Error 1")).when(teamChangeRequest).treat(eq(TreatTeamStatus.APPROVED), any(Project.class), any(Project.class), any(Developer.class));
        when(loadTeamChangeRequestPortMock.loadTeamChangeRequest(any(LoadTeamChangeRequestCommand.class))).thenReturn(teamChangeRequest);

        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(exampleDeveloper);

        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(exampleProject);

        // Mock save operations to return errors
        List<String> errors = new ArrayList<>();
        errors.add("Error 1");
        when(saveTeamChangeRequestPortMock.saveTeamChangeRequest(any(SaveTeamChangeRequestCommand.class))).thenReturn(null);
        when(saveTeamPortMock.saveTeam(any(SaveTeamCommand.class))).thenReturn(new Team("projectId", new ArrayList<>(), false));

        // Treat the team change request
        TreatTeamChangeRequestCommand command = new TreatTeamChangeRequestCommand("requestId", TreatTeamStatus.APPROVED);
        TreatTeamChangeRequestResponse.Response response = treatTeamChangeRequestService.treatTeamChangeRequest(command);

        // Verify the response
        assertInstanceOf(TreatTeamChangeRequestResponse.MultipleErrorsResponse.class, response);
        assertEquals(((TreatTeamChangeRequestResponse.MultipleErrorsResponse) response).errorMessages(), errors);
    }
}
