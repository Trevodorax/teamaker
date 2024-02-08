package org.teamaker.team.application.port.out.loadTeamChangeRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoadTeamChangeRequestCommandTest {
    private static String validTeamId;

    @BeforeAll
    public static void setUp() {
        validTeamId = "validTeamId";
    }

    @Test
    public void testConstructorValidData() {
        LoadTeamChangeRequestCommand command = new LoadTeamChangeRequestCommand(validTeamId);
        assertEquals(validTeamId, command.getRequestId());
    }

    @Test
    public void testConstructorEmptyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LoadTeamChangeRequestCommand(null));
    }
}
