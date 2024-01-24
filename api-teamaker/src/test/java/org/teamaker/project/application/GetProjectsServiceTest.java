package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.getProjects.GetProjectsResponse;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class GetProjectsServiceTest {
    private static LoadProjectsPort loadProjectsPortMock;
    private static GetProjectsService getProjectsService;

    @BeforeAll
    public static void setUp() {
        loadProjectsPortMock = mock(LoadProjectsPort.class);
        getProjectsService = new GetProjectsService(loadProjectsPortMock);
    }

    @Test
    public void testGetProjects_Success() {
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);

        List<Project> expectedProjects = List.of(
                new Project("577c2860-b584-4d27-94d8-21b10c095aac", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate),
                new Project("577c2860-b584-4d27-94d8-21b10c095aad", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate),
                new Project("577c2860-b584-4d27-94d8-21b10c095aae", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate)
        );

        when(loadProjectsPortMock.loadProjects()).thenReturn(expectedProjects);

        GetProjectsResponse.Response result = getProjectsService.getProjects();

        verify(loadProjectsPortMock).loadProjects();
        assertInstanceOf(GetProjectsResponse.SuccessResponse.class, result);
        assertEquals(expectedProjects.stream().map(Project::toResponse).toList(), ((GetProjectsResponse.SuccessResponse) result).projects());

        assertEquals(expectedProjects.size(), ((GetProjectsResponse.SuccessResponse) result).projects().size());
    }

    @Test
    public void testGetProjects_NoProjectsFound() {
        when(loadProjectsPortMock.loadProjects()).thenReturn(List.of());

        GetProjectsResponse.Response result = getProjectsService.getProjects();

        verify(loadProjectsPortMock).loadProjects();
        assertInstanceOf(GetProjectsResponse.SuccessResponse.class, result);
        assertEquals(List.of(), ((GetProjectsResponse.SuccessResponse) result).projects());

        assertEquals(0, ((GetProjectsResponse.SuccessResponse) result).projects().size());
    }
}