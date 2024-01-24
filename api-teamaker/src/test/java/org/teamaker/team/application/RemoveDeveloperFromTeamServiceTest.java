package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamCommand;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamResponse;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RemoveDeveloperFromTeamServiceTest {
    private static RemoveDeveloperFromTeamService removeDeveloperFromTeamService;
    private static LoadDeveloperPort loadDeveloperPortMock;
    private static LoadProjectPort loadProjectPortMock;
    private static LoadDeveloperProjectsPort loadDeveloperProjectsPortMock;
    private static SaveTeamPort saveTeamPortMock;
    private static LoadTeamPort loadTeamPortMock;

    @BeforeAll
    public static void setUp() {
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        loadDeveloperProjectsPortMock = mock(LoadDeveloperProjectsPort.class);
        loadTeamPortMock = mock(LoadTeamPort.class);
        saveTeamPortMock = mock(SaveTeamPort.class);

        removeDeveloperFromTeamService = new RemoveDeveloperFromTeamService(
                loadDeveloperPortMock,
                loadProjectPortMock,
                loadDeveloperProjectsPortMock,
                saveTeamPortMock,
                loadTeamPortMock
        );
    }

    @Test
    public void testRemoveDeveloperFromTeam_NotPartOfTeam() {
        // mock out ports
        Project mockProject = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10),
                new Team("id", new ArrayList<>(), false)
        );
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        Developer mockDeveloper = new Developer("id", "name", "email", LocalDate.of(2023, 1, 1));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(new ArrayList<>());

        Team mockTeam = new Team("projectId", getDevelopersForValidTeam(), false);
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(mockTeam);
        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(mockTeam);

        // call
        RemoveDeveloperFromTeamResponse.Response response = removeDeveloperFromTeamService.removeDeveloperFromTeam(
                new RemoveDeveloperFromTeamCommand(mockDeveloper.getDeveloperId(), mockProject.getProjectId())
        );

        assertTrue(response instanceof RemoveDeveloperFromTeamResponse.SingleErrorResponse);
        assertEquals("Developer isn't in this team.", ((RemoveDeveloperFromTeamResponse.SingleErrorResponse) response).errorMessage());
    }

    @Test
    public void testRemoveDeveloperFromTeam_ProjectAlreadyStarted() {
        // mock out ports
        Project mockProject = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.ACCEPTED,
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(2),
                new Team("id", new ArrayList<>(), false)
        );
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        Developer mockDeveloper = new Developer("id", "name", "email", LocalDate.of(2023, 1, 1));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(List.of(mockProject));

        Team mockTeam = new Team("projectId", getDevelopersForValidTeam(), false);
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(mockTeam);
        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(mockTeam);

        // call
        RemoveDeveloperFromTeamResponse.Response response = removeDeveloperFromTeamService.removeDeveloperFromTeam(
                new RemoveDeveloperFromTeamCommand(mockDeveloper.getDeveloperId(), mockProject.getProjectId())
        );

        assertTrue(response instanceof RemoveDeveloperFromTeamResponse.SingleErrorResponse);
        assertEquals("Project has already started or is done", ((RemoveDeveloperFromTeamResponse.SingleErrorResponse) response).errorMessage());
    }

    @Test
    public void testRemoveDeveloperFromTeam_ProjectRulesNotRespected() {
        // mock out ports
        Project mockProject = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10),
                new Team("id", new ArrayList<>(), false)
        );
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        Developer mockDeveloper = new Developer("dev1", "name", "email", LocalDate.of(2023, 1, 1));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(List.of(mockProject));

        Team mockTeam = new Team("projectId", getDevelopersForValidTeam(), true);
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(mockTeam);
        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(mockTeam);

        // call
        RemoveDeveloperFromTeamResponse.Response response = removeDeveloperFromTeamService.removeDeveloperFromTeam(
                new RemoveDeveloperFromTeamCommand("dev1", mockProject.getProjectId())
        );

        assertTrue(response instanceof RemoveDeveloperFromTeamResponse.MultipleErrorsResponse);
        assertEquals("Not enough developers in team.", ((RemoveDeveloperFromTeamResponse.MultipleErrorsResponse) response).errorMessages().get(0));
    }

    @Test
    public void testRemoveDeveloperFromTeam_Success() {
        // mock out ports
        Project mockProject = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10),
                new Team("id", new ArrayList<>(), false)
        );
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        Developer mockDeveloper = new Developer("devX", "name", "email", LocalDate.of(2023, 1, 1));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(List.of(mockProject));

        List<Developer> developers = getDevelopersForValidTeam();
        developers.add(mockDeveloper);
        Team mockTeam = new Team("projectId", developers, true);
        when(saveTeamPortMock.saveTeam(any(Team.class))).thenReturn(mockTeam);
        when(loadTeamPortMock.loadTeam(any(LoadTeamCommand.class))).thenReturn(mockTeam);

        // call
        RemoveDeveloperFromTeamResponse.Response response = removeDeveloperFromTeamService.removeDeveloperFromTeam(
                new RemoveDeveloperFromTeamCommand("devX", mockProject.getProjectId())
        );


        // checks
        assertTrue(response instanceof RemoveDeveloperFromTeamResponse.SuccessResponse);
        assertEquals(((RemoveDeveloperFromTeamResponse.SuccessResponse) response).developer(), mockDeveloper.toResponse());

        ArgumentCaptor<Team> captor = ArgumentCaptor.forClass(Team.class);
        verify(saveTeamPortMock).saveTeam(captor.capture());
        Team savedTeam = captor.getValue();
        assertEquals(savedTeam, mockTeam);
    }

    private List<Developer> getDevelopersForValidTeam() {
        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(new Developer("dev1", "Paul", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer("dev2", "Tom", "p@gmail.com", LocalDate.of(2019, 1, 1)));
        developers.add(new Developer("dev3", "Anaelle", "p@gmail.com", LocalDate.of(2019, 1, 1)));

        return developers;
    }
}
