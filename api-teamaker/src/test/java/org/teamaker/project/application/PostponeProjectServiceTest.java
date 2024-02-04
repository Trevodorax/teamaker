package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectCommand;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectResponse;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostponeProjectServiceTest {
    private static SaveProjectPort saveProjectPortMock;
    private static LoadProjectPort loadProjectPortMock;
    private static PostponeProjectService postponeProjectService;

    @BeforeEach
    public void setUp() {
        saveProjectPortMock = mock(SaveProjectPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        postponeProjectService = new PostponeProjectService(saveProjectPortMock, loadProjectPortMock);
    }

    @Test
    public void testPostponeProject() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        LocalDate mockNewStartDate = mockStartDate.plusDays(1);
        LocalDate mockNewEndDate = mockEndDate.plusDays(1);

        Project mockInitialProject = new Project(mockId, "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING, mockStartDate, mockEndDate, new Team("id", new ArrayList<>(), false), Map.of());
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockInitialProject);

        Project expectedProject = new Project(mockId, "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING, mockNewStartDate, mockNewEndDate, new Team("id", new ArrayList<>(), false), Map.of());
        when(saveProjectPortMock.saveProject(any())).thenReturn(expectedProject);

        PostponeProjectCommand command = new PostponeProjectCommand(mockId, mockNewStartDate, mockNewEndDate);

        PostponeProjectResponse.Response response = postponeProjectService.postponeProject(command);

        verify(loadProjectPortMock).loadProject(any(LoadProjectCommand.class));
        verify(saveProjectPortMock).saveProject(any(SaveProjectCommand.class));

        assertInstanceOf(PostponeProjectResponse.SuccessResponse.class, response);

        assertEquals(expectedProject.getProjectId(), ((PostponeProjectResponse.SuccessResponse) response).project().projectId());
        assertEquals(expectedProject.getStartDate(), ((PostponeProjectResponse.SuccessResponse) response).project().newStartDate());
        assertEquals(expectedProject.getEndDate(), ((PostponeProjectResponse.SuccessResponse) response).project().newEndDate());
    }

    @Test
    public void testPostponeProjectNotPending() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        LocalDate mockNewStartDate = mockStartDate.plusDays(1);
        LocalDate mockNewEndDate = mockEndDate.plusDays(1);

        Project mockInitialProject = new Project(mockId, "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.ACCEPTED, mockStartDate, mockEndDate, new Team(mockId, new ArrayList<>(), false), Map.of());
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockInitialProject);

        PostponeProjectCommand command = new PostponeProjectCommand(mockId, mockNewStartDate, mockNewEndDate);

        PostponeProjectResponse.Response response = postponeProjectService.postponeProject(command);

        assertInstanceOf(PostponeProjectResponse.ErrorResponse.class, response);
        assertEquals("Project cannot be postponed if it is not pending", ((PostponeProjectResponse.ErrorResponse) response).errorMessage());

        verify(loadProjectPortMock).loadProject(any(LoadProjectCommand.class));
        verify(saveProjectPortMock, never()).saveProject(any(SaveProjectCommand.class));
    }
}
