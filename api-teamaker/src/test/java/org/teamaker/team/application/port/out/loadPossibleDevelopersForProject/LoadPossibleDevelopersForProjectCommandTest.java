package org.teamaker.team.application.port.out.loadPossibleDevelopersForProject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class LoadPossibleDevelopersForProjectCommandTest {
    private static String validProjectId;

    @BeforeAll
    public static void setUp() {
        validProjectId = "validProjectId";
    }

    @Test
    public void testConstructorValidData() {
        LoadPossibleDevelopersForProjectCommand command = new LoadPossibleDevelopersForProjectCommand(validProjectId);
        assertEquals(validProjectId, command.getProjectId());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LoadPossibleDevelopersForProjectCommand(null));
    }
}