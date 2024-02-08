package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectResponse;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.out.createProject.CreateProjectCommand;
import org.teamaker.project.application.port.out.createProject.CreateProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class SubmitProjectServiceTest {
    private static CreateProjectPort createProjectPortMock;
    private static SubmitProjectService submitProjectService;

    @BeforeAll
    public static void setUp() {
        createProjectPortMock = mock(CreateProjectPort.class);
        submitProjectService = new SubmitProjectService(createProjectPortMock);
    }

    @Test
    public void testSubmitProject() {
        // === given === //
        // args for submitProject()
        String mockName = "Project Name";
        String mockDescription = "Project Description";
        ProjectPriority mockPriority = ProjectPriority.CRITICAL;
        ProjectStatus mockStatus = ProjectStatus.PENDING;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        Map<String, Integer> mockTechnologies = Map.of(UUID.randomUUID().toString(), 1);
        SubmitProjectCommand command = new SubmitProjectCommand(mockName, mockDescription, mockPriority, mockStartDate, mockEndDate, mockTechnologies);

        // mock the injected command
        Project expectedProject = new Project("test-id", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate, new Team("projectId", new ArrayList<>(), false), Map.of());
        when(createProjectPortMock.createProject(any(CreateProjectCommand.class))).thenReturn(expectedProject); // mock createProject method

        // === when === //
        SubmitProjectResponse.Response result = submitProjectService.submitProject(command);

        // === then === //
        ArgumentCaptor<CreateProjectCommand> captor = ArgumentCaptor.forClass(CreateProjectCommand.class);
        verify(createProjectPortMock).createProject(captor.capture());
        CreateProjectCommand capturedCommand = captor.getValue();

        assertEquals(mockName, capturedCommand.getName());
        assertEquals(mockDescription, capturedCommand.getDescription());
        assertEquals(mockPriority, capturedCommand.getPriority());
        assertEquals(mockStartDate, capturedCommand.getStartDate());
        assertEquals(mockEndDate, capturedCommand.getEndDate());

        assertInstanceOf(SubmitProjectResponse.SuccessResponse.class, result);
    }
}


