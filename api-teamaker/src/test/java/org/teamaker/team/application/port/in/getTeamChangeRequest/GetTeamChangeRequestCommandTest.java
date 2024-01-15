package org.teamaker.team.application.port.in.getTeamChangeRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetTeamChangeRequestCommandTest {
    private static String validTeamId;

    @BeforeAll
    public static void setUp() {
        validTeamId = "validTeamId";
    }

    @Test
    public void testConstructorValidData() {
        GetTeamChangeRequestCommand command = new GetTeamChangeRequestCommand(validTeamId);
        assertEquals(validTeamId, command.getTeamChangeRequestId());
    }

    @Test
    public void testConstructorEmptyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetTeamChangeRequestCommand(null));
    }
}