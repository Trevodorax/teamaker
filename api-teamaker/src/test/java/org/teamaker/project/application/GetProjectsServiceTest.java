package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.out.LoadProjectsPort;
import org.teamaker.project.domain.Project;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testGetProjects() {
        List<Project> expectedProjects = List.of(
                new Project("577c2860-b584-4d27-94d8-21b10c095aac"),
                new Project("577c2860-b584-4d27-94d8-21b10c095aad"),
                new Project("577c2860-b584-4d27-94d8-21b10c095aae")
        );

        when(loadProjectsPortMock.loadProjects()).thenReturn(expectedProjects);

        List<Project> result = getProjectsService.getProjects();

        verify(loadProjectsPortMock).loadProjects();
        assertEquals(expectedProjects, result);
    }
}