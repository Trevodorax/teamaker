package org.teamaker.team.application.port.in.getPossibleDevelopersForProject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.*;

class GetPossibleDevelopersForProjectCommandTest {
    private static String validProjectId;

    @BeforeAll
    public static void setUp() {
        validProjectId = "validProjectId";
    }

    @Test
    public void testConstructorValidData() {
        GetPossibleDevelopersForProjectCommand command = new GetPossibleDevelopersForProjectCommand(validProjectId);
        assertEquals(validProjectId, command.getProjectId());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetPossibleDevelopersForProjectCommand(null));
    }
}