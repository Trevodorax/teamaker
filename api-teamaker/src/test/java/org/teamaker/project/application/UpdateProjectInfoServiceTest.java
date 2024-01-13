package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateProjectInfoServiceTest {
    private static SaveProjectPort saveProjectPortMock;
    private static LoadProjectPort loadProjectPortMock;
    private static UpdateProjectInfoService updateProjectInfoService;

    @BeforeAll
    public static void setUp() {
        saveProjectPortMock = mock(SaveProjectPort.class);
        loadProjectPortMock = mock(LoadProjectPort.class);
        updateProjectInfoService = new UpdateProjectInfoService(loadProjectPortMock, saveProjectPortMock);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(saveProjectPortMock);
        Mockito.reset(loadProjectPortMock);
    }

    @Test
    public void testUpdateProjectInfo() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now();
        LocalDate mockEndDate = LocalDate.now().plusDays(5);

        String mockNewName = "New Project Name";
        String mockNewDescription = "New Project Description";
        ProjectPriority mockNewPriority = ProjectPriority.NORMAL;

        Project initialProject = new Project(mockId, mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate);
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(initialProject);

        Project expectedProject = new Project(mockId, mockNewName, mockNewDescription, mockNewPriority, mockStatus, mockStartDate, mockEndDate);
        when(saveProjectPortMock.saveProject(any())).thenReturn(expectedProject);

        UpdateProjectInfoCommand command = new UpdateProjectInfoCommand(mockId, mockNewName, mockNewDescription, mockNewPriority);
        updateProjectInfoService.updateProjectInfo(command);

        ArgumentCaptor<SaveProjectCommand> captor = ArgumentCaptor.forClass(SaveProjectCommand.class);
        verify(saveProjectPortMock).saveProject(captor.capture());
        SaveProjectCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getProject().getProjectId());
        assertEquals(mockNewName, capturedCommand.getProject().getName());
        assertEquals(mockNewDescription, capturedCommand.getProject().getDescription());
        assertEquals(mockNewPriority, capturedCommand.getProject().getPriority());
    }

    @Test
    public void testUpdateProjectInfoNullName() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now();
        LocalDate mockEndDate = LocalDate.now().plusDays(5);

        String mockNewDescription = "New Project Description";
        ProjectPriority mockNewPriority = ProjectPriority.NORMAL;

        Project initialProject = new Project(mockId, mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate);
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(initialProject);

        Project expectedProject = new Project(mockId, mockName, mockNewDescription, mockNewPriority, mockStatus, mockStartDate, mockEndDate);
        when(saveProjectPortMock.saveProject(any())).thenReturn(expectedProject);

        UpdateProjectInfoCommand command = new UpdateProjectInfoCommand(mockId, null, mockNewDescription, mockNewPriority);
        updateProjectInfoService.updateProjectInfo(command);

        ArgumentCaptor<SaveProjectCommand> captor = ArgumentCaptor.forClass(SaveProjectCommand.class);
        verify(saveProjectPortMock).saveProject(captor.capture());
        SaveProjectCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getProject().getProjectId());
        assertEquals(mockName, capturedCommand.getProject().getName());
        assertEquals(mockNewDescription, capturedCommand.getProject().getDescription());
        assertEquals(mockNewPriority, capturedCommand.getProject().getPriority());
    }

}