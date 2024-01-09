package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.project.application.port.in.getProject.GetProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Priority;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetProjectServiceTest {
    private static LoadProjectPort loadProjectPortMock;
    private static GetProjectService getProjectService;

    @BeforeAll
    public static void setUp() {
        loadProjectPortMock = mock(LoadProjectPort.class);
        getProjectService = new GetProjectService(loadProjectPortMock);
    }

    @Test
    public void testGetProject() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        Priority mockPriority = Priority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        GetProjectCommand command = new GetProjectCommand(mockId);

        Project expectedProject = new Project(mockId, mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate);
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(expectedProject);

        Project result = getProjectService.getProject(command);

        ArgumentCaptor<LoadProjectCommand> captor = ArgumentCaptor.forClass(LoadProjectCommand.class);
        verify(loadProjectPortMock).loadProject(captor.capture());
        LoadProjectCommand capturedCommand = captor.getValue();
        
        assertEquals(mockId, capturedCommand.getProjectId());
        assertEquals(expectedProject, result);
    }

}