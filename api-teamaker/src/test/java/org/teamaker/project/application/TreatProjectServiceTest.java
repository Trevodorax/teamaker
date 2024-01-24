package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.treatProject.TreatProjectCommand;
import org.teamaker.project.application.port.in.treatProject.TreatProjectResponse;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TreatProjectServiceTest {
    private static SaveProjectPort saveProjectPortMock;
    private static LoadProjectPort loadProjectPortMock;
    private static TreatProjectService treatProjectService;

    @BeforeEach
    public void setUp() {
        saveProjectPortMock = mock(SaveProjectPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        treatProjectService = new TreatProjectService(loadProjectPortMock, saveProjectPortMock);
    }

    @Test
    public void testTreatProject() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        ProjectStatus status = ProjectStatus.PENDING;

        Project initialProject = new Project(mockId, "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(5));
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(initialProject);

        Project expectedProject = new Project(mockId, "Project Name", "Project Description", ProjectPriority.CRITICAL, status, LocalDate.now(), LocalDate.now().plusDays(5));
        when(saveProjectPortMock.saveProject(any())).thenReturn(expectedProject);

        TreatProjectCommand command = new TreatProjectCommand(mockId, status);
        TreatProjectResponse.Response response = treatProjectService.treatProject(command);

        verify(loadProjectPortMock).loadProject(any(LoadProjectCommand.class));
        verify(saveProjectPortMock).saveProject(any(SaveProjectCommand.class));

        assertInstanceOf(TreatProjectResponse.SuccessResponse.class, response);

        assertEquals(expectedProject.getProjectId(), ((TreatProjectResponse.SuccessResponse) response).project().projectId());
        assertEquals(expectedProject.getStatus(), ((TreatProjectResponse.SuccessResponse) response).project().status());
    }



    @Test
    public void testTreatProjectNotPending() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        ProjectStatus status = ProjectStatus.ACCEPTED;

        Project mockInitialProject = new Project(mockId, "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.ACCEPTED, LocalDate.now(), LocalDate.now().plusDays(5));
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(mockInitialProject);

        TreatProjectCommand command = new TreatProjectCommand(mockId, status);
        assertThrows(IllegalStateException.class, () -> treatProjectService.treatProject(command));

        verify(loadProjectPortMock).loadProject(any(LoadProjectCommand.class));
        verify(saveProjectPortMock, never()).saveProject(any(SaveProjectCommand.class));
    }
}
