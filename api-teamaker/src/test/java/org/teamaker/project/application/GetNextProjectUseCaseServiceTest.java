package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectResponse;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetNextProjectUseCaseServiceTest {
    private static FindNextProjectPort findNextProjectMock;
    private static GetNextProjectService getNextProjectService;

    @BeforeAll
    public static void setUp() {
        findNextProjectMock = mock(FindNextProjectPort.class);
        getNextProjectService = new GetNextProjectService(findNextProjectMock);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(findNextProjectMock);
    }

    @Test
    public void testGetNextProject() {
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        Project expectedProject = new Project("577c2860-b584-4d27-94d8-21b10c095aac", "Project Name", "Project Description", ProjectPriority.CRITICAL, ProjectStatus.PENDING, mockStartDate, mockStartDate.plusDays(5), new Team("projectId", new ArrayList<>(), false), Map.of());

        when(findNextProjectMock.findNextProject()).thenReturn(expectedProject);

        GetNextProjectResponse.Response result = getNextProjectService.getNextProject();

        verify(findNextProjectMock).findNextProject();
        assertInstanceOf(GetNextProjectResponse.SuccessResponse.class, result);
        assertEquals(expectedProject.toResponse(), ((GetNextProjectResponse.SuccessResponse) result).project());
    }

    @Test
    public void testGetNextProjectNotFound() {
        when(findNextProjectMock.findNextProject()).thenThrow(new NoSuchElementException("No future projects found"));

        GetNextProjectResponse.Response result = getNextProjectService.getNextProject();

        verify(findNextProjectMock).findNextProject();
        assertEquals(result, new GetNextProjectResponse.ErrorResponse("No future projects found"));
    }
}