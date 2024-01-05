package org.teamaker.project.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.teamaker.project.application.port.in.SubmitProjectCommand;
import org.teamaker.project.application.port.out.CreateProjectCommand;
import org.teamaker.project.application.port.out.CreateProjectPort;
import org.teamaker.project.domain.Priority;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.Status;

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
        Priority mockPriority = Priority.CRITICAL;
        Status mockStatus = Status.OPEN;
        LocalDate mockStartDate = LocalDate.now().plusDays(1);
        LocalDate mockEndDate = mockStartDate.plusDays(5);
        ArrayList<UUID> mockTechnologies = new ArrayList<>();
        SubmitProjectCommand command = new SubmitProjectCommand(mockName, mockDescription, mockPriority, mockStartDate, mockEndDate, mockTechnologies);

        // mock the injected command
        Project expectedProject = new Project("test-id", mockName, mockDescription, mockPriority, mockStatus, mockStartDate, mockEndDate);
        when(createProjectPortMock.createProject(any(CreateProjectCommand.class))).thenReturn(expectedProject); // mock createProject method

        // === when === //
        Project result = submitProjectService.submitProject(command);

        // === then === //
        ArgumentCaptor<CreateProjectCommand> captor = ArgumentCaptor.forClass(CreateProjectCommand.class);
        verify(createProjectPortMock).createProject(captor.capture());
        CreateProjectCommand capturedCommand = captor.getValue();

        assertEquals(mockName, capturedCommand.getName());
        assertEquals(mockDescription, capturedCommand.getDescription());
        assertEquals(mockPriority, capturedCommand.getPriority());
        assertEquals(mockStartDate, capturedCommand.getStartDate());
        assertEquals(mockEndDate, capturedCommand.getEndDate());
        assertEquals(expectedProject, result);
    }
}


