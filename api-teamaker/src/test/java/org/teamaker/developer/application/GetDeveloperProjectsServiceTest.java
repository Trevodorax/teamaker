package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsCommand;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsResponse;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetDeveloperProjectsServiceTest {
    private static LoadDeveloperProjectsPort loadDeveloperProjectsPortMock;
    private static GetDeveloperProjectsService getDeveloperProjectsServiceMock;

    @BeforeEach
    public void setUp() {
        loadDeveloperProjectsPortMock = mock(LoadDeveloperProjectsPort.class);
        getDeveloperProjectsServiceMock = new GetDeveloperProjectsService(loadDeveloperProjectsPortMock);
    }

    @Test
    public void testGetDeveloperProjectsSuccess() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        GetDeveloperProjectsCommand command = new GetDeveloperProjectsCommand(mockId);

        List<Project> expectedProjectsList = List.of(new Project(mockId, mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate, new Team("id", new ArrayList<>(), false), Map.of()));
        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenReturn(expectedProjectsList);

        GetDeveloperProjectsResponse.Response result = getDeveloperProjectsServiceMock.getDeveloperProjects(command);
        ArgumentCaptor<LoadDeveloperProjectsCommand> captor = ArgumentCaptor.forClass(LoadDeveloperProjectsCommand.class);
        verify(loadDeveloperProjectsPortMock).loadDeveloperProjects(captor.capture());
        LoadDeveloperProjectsCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getDeveloperId());
        assertInstanceOf(GetDeveloperProjectsResponse.SuccessResponse.class, result);
        assertEquals(new GetDeveloperProjectsResponse.SuccessResponse(expectedProjectsList), result);
    }

    @Test
    public void testGetDeveloperProjectsError() {
        String mockId = "577c2860-b584-4d27-94d8-21b10c095aac";
        GetDeveloperProjectsCommand command = new GetDeveloperProjectsCommand(mockId);

        when(loadDeveloperProjectsPortMock.loadDeveloperProjects(any(LoadDeveloperProjectsCommand.class))).thenThrow(new NoSuchElementException("developer not found"));

        GetDeveloperProjectsResponse.Response result = getDeveloperProjectsServiceMock.getDeveloperProjects(command);
        ArgumentCaptor<LoadDeveloperProjectsCommand> captor = ArgumentCaptor.forClass(LoadDeveloperProjectsCommand.class);
        verify(loadDeveloperProjectsPortMock).loadDeveloperProjects(captor.capture());
        LoadDeveloperProjectsCommand capturedCommand = captor.getValue();

        assertEquals(mockId, capturedCommand.getDeveloperId());
        assertInstanceOf(GetDeveloperProjectsResponse.ErrorResponse.class, result);
        assertEquals(new GetDeveloperProjectsResponse.ErrorResponse("developer not found"), result);
    }
}
