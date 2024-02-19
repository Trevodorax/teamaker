package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubmitTeamChangeRequestCommandTest {
    private static String validDeveloperId;
    private static String validProjectId;
    private static String validToProjectId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validProjectId = "validProjectId";
        validToProjectId = "validToProjectId";
    }

    @Test
    void testConstructorValidData() {
        SubmitTeamChangeRequestCommand command = new SubmitTeamChangeRequestCommand(validDeveloperId, validProjectId, validToProjectId);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertEquals(validProjectId, command.getRequestedProjectId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new SubmitTeamChangeRequestCommand(null, validProjectId, validToProjectId));
    }

    @Test
    void testConstructorNullId() {
        assertThrows(ConstraintViolationException.class,
                () -> new SubmitTeamChangeRequestCommand(validDeveloperId, null, validToProjectId));
    }
}