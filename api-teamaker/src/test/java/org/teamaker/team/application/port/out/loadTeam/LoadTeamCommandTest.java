package org.teamaker.team.application.port.out.loadTeam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.validation.ConstraintViolationException;

class LoadTeamCommandTest {
    private static String validTeamId;

    @BeforeAll
    public static void setUp() {
        validTeamId = "validTeamId";
    }

    @Test
    public void testConstructorValidData() {
        LoadTeamCommand command = new LoadTeamCommand(validTeamId);
        assertEquals(validTeamId, command.getId());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LoadTeamCommand(null));
    }
}
