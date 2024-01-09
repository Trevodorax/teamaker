package org.teamaker.team.application.port.in.assignDeveloperToTeam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.*;

class AssignDeveloperToTeamCommandTest {
    private static String validDeveloperId;
    private static String validProjectId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validProjectId = "validProjectId";
    }

    @Test
    void testConstructorValidData() {
        AssignDeveloperToTeamCommand command = new AssignDeveloperToTeamCommand(validDeveloperId, validProjectId);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertEquals(validProjectId, command.getProjectId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new AssignDeveloperToTeamCommand(null, validProjectId));
    }

    @Test
    void testConstructorNullProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new AssignDeveloperToTeamCommand(validDeveloperId, null));
    }
}
