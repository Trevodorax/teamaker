package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoCommand;
import org.teamaker.project.domain.ProjectPriority;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class UpdateProjectInfoCommandTest {
    private static String validProjectId = "validProjectId";
    private static String validName = "validName";
    private static String validDescription = "validDescription";
    private static ProjectPriority validPriority = ProjectPriority.CRITICAL;

    @BeforeAll
    public static void setUp() {
        validProjectId = "validProjectId";
        validName = "validName";
        validDescription = "validDescription";
        validPriority = ProjectPriority.CRITICAL;
    }

    @Test
    public void testConstructorValidData() {
        UpdateProjectInfoCommand command = new UpdateProjectInfoCommand(validProjectId, validName, validDescription, validPriority);
        assertEquals(validProjectId, command.getProjectId());
        assertEquals(validName, command.getName());
        assertEquals(validDescription, command.getDescription());
        assertEquals(validPriority, command.getPriority());
    }

    @Test
    public void testConstructorEmptyProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateProjectInfoCommand(null, validName, validDescription, validPriority));
    }

    @Test
    public void testConstructorEmptyName() {
        UpdateProjectInfoCommand command = new UpdateProjectInfoCommand(validProjectId, null, validDescription, validPriority);
        assertEquals(validProjectId, command.getProjectId());
        assertNull(command.getName());
        assertEquals(validDescription, command.getDescription());
        assertEquals(validPriority, command.getPriority());
    }

    @Test
    public void testConstructorEmptyDescription() {
        UpdateProjectInfoCommand command = new UpdateProjectInfoCommand(validProjectId, validName, null, validPriority);
        assertEquals(validProjectId, command.getProjectId());
        assertEquals(validName, command.getName());
        assertNull(command.getDescription());
        assertEquals(validPriority, command.getPriority());
    }

    @Test
    public void testConstructorEmptyPriority() {
        UpdateProjectInfoCommand command = new UpdateProjectInfoCommand(validProjectId, validName, validDescription, null);
        assertEquals(validProjectId, command.getProjectId());
        assertEquals(validName, command.getName());
        assertEquals(validDescription, command.getDescription());
        assertNull(command.getPriority());
    }

}