package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamCommand;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamResponse;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignDeveloperToTeamServiceTest {
    private AssignDeveloperToTeamService assignDeveloperToTeamService;

    @Mock
    private LoadDeveloperPort loadDeveloperPort;

    @Mock
    private LoadProjectPort loadProjectPort;

    @Mock
    private LoadDeveloperProjectsPort loadDeveloperProjectsPort;

    @Mock
    private LoadTeamPort loadTeamPort;

    @Mock
    private SaveTeamPort saveTeamPort;
    @Mock
    private LoadDeveloperSkillsPort loadDeveloperSkillsPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assignDeveloperToTeamService = new AssignDeveloperToTeamService(loadDeveloperPort, loadProjectPort, loadDeveloperProjectsPort, loadTeamPort, saveTeamPort, loadDeveloperSkillsPort);
    }

    @Test
    void testAssignDeveloperToTeam_Success() {
        // Mock developer, projects, project, and team data
        Developer mockDeveloper = new Developer("developerId", "John Doe", "john@example.com", LocalDate.of(2018, 6, 6), null);
        when(loadDeveloperPort.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        List<Project> mockDeveloperProjects = new ArrayList<>();
        when(loadDeveloperProjectsPort.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(mockDeveloperProjects);

        Team mockTeam = new Team("projectId", new ArrayList<>(), false);
        when(loadTeamPort.loadTeam(any(LoadTeamCommand.class))).thenReturn(mockTeam);

        Project mockProject = new Project("projectId", "Project Name", "Project Description", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED, LocalDate.of(2022, 6, 6), LocalDate.of(2023, 6, 6), mockTeam, Map.of());
        when(loadProjectPort.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        when(saveTeamPort.saveTeam(any(SaveTeamCommand.class))).thenReturn(mockTeam);

        // Assign developer to the team
        AssignDeveloperToTeamCommand command = new AssignDeveloperToTeamCommand("developerId", "projectId");
        AssignDeveloperToTeamResponse.Response response = assignDeveloperToTeamService.assignDeveloperToTeam(command);

        // Verify the response and that saveTeam was called
        assertInstanceOf(AssignDeveloperToTeamResponse.SuccessResponse.class, response);
        assertEquals(List.of(mockDeveloper.toResponse()), ((AssignDeveloperToTeamResponse.SuccessResponse) response).developer());

        ArgumentCaptor<SaveTeamCommand> captor = ArgumentCaptor.forClass(SaveTeamCommand.class);
        verify(saveTeamPort).saveTeam(captor.capture());
        assertEquals(mockTeam, captor.getValue().getTeam());
    }

    @Test
    void testAssignDeveloperToTeam_DeveloperNotAvailable() {
        // Mock developer, projects, project, and team data
        Developer mockDeveloper = new Developer("developerId", "John Doe", "john@example.com", LocalDate.of(2018, 6, 6), null);
        when(loadDeveloperPort.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        List<Project> mockDeveloperProjects = new ArrayList<>();
        when(loadDeveloperProjectsPort.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(mockDeveloperProjects);

        Team mockTeam = new Team("projectId", new ArrayList<>(), false);
        when(loadTeamPort.loadTeam(any(LoadTeamCommand.class))).thenReturn(mockTeam);

        Project mockProject = new Project("projectId", "Project Name", "Project Description", ProjectPriority.NORMAL, ProjectStatus.ACCEPTED, LocalDate.of(2022, 6, 6), LocalDate.of(2023, 6, 6), mockTeam, Map.of());
        when(loadProjectPort.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        // Developer is not available for this project
        mockDeveloperProjects.add(mockProject);

        // Assign developer to the team
        AssignDeveloperToTeamCommand command = new AssignDeveloperToTeamCommand("developerId", "projectId");
        AssignDeveloperToTeamResponse.Response response = assignDeveloperToTeamService.assignDeveloperToTeam(command);

        // Verify the response
        assertTrue(response instanceof AssignDeveloperToTeamResponse.SingleErrorResponse);
        assertEquals("Developer is not available for this project.", ((AssignDeveloperToTeamResponse.SingleErrorResponse) response).errorMessage());

        // Verify that saveTeam was not called
        verify(saveTeamPort, never()).saveTeam(any(SaveTeamCommand.class));
    }
}
