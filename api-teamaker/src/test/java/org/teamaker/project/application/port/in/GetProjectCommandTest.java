package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetProjectCommandTest {
    private static String validProjectId;

    @BeforeAll
    public static void setUp() {
        validProjectId = "validProjectId";
    }

    @Test
    public void testConstructorValidData() {
        GetProjectCommand command = new GetProjectCommand(validProjectId);
        assertEquals(validProjectId, command.getProjectId());
    }

    @Test
    public void testConstructorEmptyProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetProjectCommand(null));
    }

}