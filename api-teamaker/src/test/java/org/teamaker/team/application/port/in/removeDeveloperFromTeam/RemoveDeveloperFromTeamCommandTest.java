package org.teamaker.team.application.port.in.removeDeveloperFromTeam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveDeveloperFromTeamCommandTest {
    private static String validDeveloperId;
    private static String validProjectId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validProjectId = "validProjectId";
    }

    @Test
    void testConstructorValidData() {
        RemoveDeveloperFromTeamCommand command = new RemoveDeveloperFromTeamCommand(validDeveloperId, validProjectId);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertEquals(validProjectId, command.getProjectId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new RemoveDeveloperFromTeamCommand(null, validProjectId));
    }

    @Test
    void testConstructorNullProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new RemoveDeveloperFromTeamCommand(validDeveloperId, null));
    }
}
