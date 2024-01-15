package org.teamaker.team.application.port.in.getTeam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetTeamCommandTest {
    private static String validTeamId;

    @BeforeAll
    public static void setUp() {
        validTeamId = "validTeamId";
    }

    @Test
    public void testConstructorValidData() {
        GetTeamCommand command = new GetTeamCommand(validTeamId);
        assertEquals(validTeamId, command.getTeamId());
    }

    @Test
    public void testConstructorEmptyTeamId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetTeamCommand(null));
    }
}