package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class SubmitTeamChangeRequestCommandTest {
    private static String validDeveloperId;
    private static String validProjectId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validProjectId = "validProjectId";
    }

    @Test
    void testConstructorValidData() {
        SubmitTeamChangeRequestCommand command = new SubmitTeamChangeRequestCommand(validDeveloperId, validProjectId);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertEquals(validProjectId, command.getRequestedProjectId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new SubmitTeamChangeRequestCommand(null, validProjectId));
    }

    @Test
    void testConstructorNullProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new SubmitTeamChangeRequestCommand(validDeveloperId, null));
    }
}