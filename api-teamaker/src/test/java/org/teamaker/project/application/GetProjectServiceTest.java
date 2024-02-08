package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.project.application.port.in.getProject.GetProjectCommand;
import org.teamaker.project.application.port.in.getProject.GetProjectResponse;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class GetProjectServiceTest {
    private static LoadProjectPort loadProjectPortMock;
    private static GetProjectService getProjectService;

    @BeforeEach
    public void setUp() {
        loadProjectPortMock = mock(LoadProjectPort.class);
        getProjectService = new GetProjectService(loadProjectPortMock);
    }

    @Test
    public void testGetProject_Success() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        GetProjectCommand command = new GetProjectCommand(mockId);

        Project expectedProject = new Project(mockId, mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate, new Team("id", new ArrayList<>(), false), Map.of());
        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenReturn(expectedProject);

        GetProjectResponse.Response result = getProjectService.getProject(command);

        ArgumentCaptor<LoadProjectCommand> captor = ArgumentCaptor.forClass(LoadProjectCommand.class);
        verify(loadProjectPortMock).loadProject(captor.capture());
        LoadProjectCommand capturedCommand = captor.getValue();
        
        assertEquals(mockId, capturedCommand.getProjectId());
        assertInstanceOf(GetProjectResponse.SuccessResponse.class, result);
        assertEquals(expectedProject.toResponse(), ((GetProjectResponse.SuccessResponse) result).project());
    }

    @Test
    public void testGetProject_Error() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        GetProjectCommand command = new GetProjectCommand(mockId);

        when(loadProjectPortMock.loadProject(any(LoadProjectCommand.class))).thenThrow(new NoSuchElementException("Invalid project ID"));

        GetProjectResponse.Response result = getProjectService.getProject(command);

        ArgumentCaptor<LoadProjectCommand> captor = ArgumentCaptor.forClass(LoadProjectCommand.class);
        verify(loadProjectPortMock).loadProject(captor.capture());
        LoadProjectCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getProjectId());
        assertEquals(result, new GetProjectResponse.ErrorResponse("Invalid project ID"));
    }

}