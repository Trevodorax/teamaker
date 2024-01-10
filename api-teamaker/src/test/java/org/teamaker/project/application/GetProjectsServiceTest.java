package org.teamaker.project.application;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import org.teamaker.project.application.port.dto.ProjectResponse;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.domain.Priority;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;

class GetProjectsServiceTest {
    private static LoadProjectsPort loadProjectsPortMock;
    private static GetProjectsService getProjectsService;

    @BeforeAll
    public static void setUp() {
        loadProjectsPortMock = mock(LoadProjectsPort.class);
        getProjectsService = new GetProjectsService(loadProjectsPortMock);
    }

    @Test
    public void testGetProjects() {
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        Priority mockPriority = Priority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);

        List<Project> expectedProjects = List.of(
                new Project("577c2860-b584-4d27-94d8-21b10c095aac", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate),
                new Project("577c2860-b584-4d27-94d8-21b10c095aad", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate),
                new Project("577c2860-b584-4d27-94d8-21b10c095aae", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate)
        );

        when(loadProjectsPortMock.loadProjects()).thenReturn(expectedProjects);

        List<ProjectResponse> result = getProjectsService.getProjects();

        verify(loadProjectsPortMock).loadProjects();
        assertEquals(expectedProjects.stream().map(Project::toResponse).toList(), result);
    }
}