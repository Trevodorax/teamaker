package org.teamaker.team.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamCommand;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AssignDeveloperToTeamServiceTest {
    private static AssignDeveloperToTeamService assignDeveloperToTeamService;
    private static LoadDeveloperPort loadDeveloperPortMock;
    private static LoadProjectPort loadProjectPortMock;
    private static LoadDeveloperProjectsPort loadDeveloperProjectsPortMock;
    private static SaveDeveloperPort saveDeveloperPortMock;

    @BeforeAll
    public static void setUp() {
        loadDeveloperPortMock = mock(LoadDeveloperPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        loadDeveloperProjectsPortMock = mock(LoadDeveloperProjectsPort.class);
        saveDeveloperPortMock = mock(SaveDeveloperPort.class);
        assignDeveloperToTeamService = new AssignDeveloperToTeamService(
                loadDeveloperPortMock,
                loadProjectPortMock,
                loadDeveloperProjectsPortMock,
                saveDeveloperPortMock);
    }

    @Test
    public void testAssignDeveloperToTeam_Success() {
        // mock out ports
        Project mockProject = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10)
        );
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject);

        Developer mockDeveloper = new Developer("id", "name", "email", LocalDate.of(2023, 1, 1));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(new ArrayList<>());

        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(mockDeveloper);

        // call
        DeveloperResponse response = assignDeveloperToTeamService.assignDeveloperToTeam(new AssignDeveloperToTeamCommand("devId", "projId"));

        // check the returned dev is the one returned by the saveDeveloper command
        assertEquals(response, mockDeveloper.toResponse());

        // check the dev has been saved with the new project
        ArgumentCaptor<SaveDeveloperCommand> captor = ArgumentCaptor.forClass(SaveDeveloperCommand.class);
        verify(saveDeveloperPortMock).saveDeveloper(captor.capture());
        SaveDeveloperCommand capturedCommand = captor.getValue();
        assertTrue(capturedCommand.getDeveloper().getProjectList().contains(mockProject));
    }

    @Test
    public void testAssignDeveloperToTeam_NotAvailable() {
        // mock out ports
        Project mockProject1 = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 10)
        );
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockProject1);

        Developer mockDeveloper = new Developer("id", "name", "email", LocalDate.of(2023, 1, 1));
        when(loadDeveloperPortMock.loadDeveloper(any(LoadDeveloperCommand.class))).thenReturn(mockDeveloper);

        Project mockProject2 = new Project("id", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING,
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 8)
        );
        ArrayList<Project> mockProjects = new ArrayList<>();
        mockProjects.add(mockProject2);
        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(mockProjects);

        when(saveDeveloperPortMock.saveDeveloper(any(SaveDeveloperCommand.class))).thenReturn(mockDeveloper);


        // call
        assertThrows(IllegalArgumentException.class,
                () -> assignDeveloperToTeamService.assignDeveloperToTeam(new AssignDeveloperToTeamCommand("devId", "projId")));
    }
}