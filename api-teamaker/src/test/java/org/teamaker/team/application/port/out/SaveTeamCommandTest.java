package org.teamaker.team.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.domain.Team;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class SaveTeamCommandTest {
    private static Team validTeam;

    @BeforeAll
    public static void setUp() {
        validTeam = new Team("projectId", null, false);
    }

    @Test
    public void testSaveTeamCommand_Success() {
        SaveTeamCommand saveTeamCommand = new SaveTeamCommand(validTeam);
        assertEquals(validTeam, saveTeamCommand.getTeam());
    }

    @Test
    public void testSaveTeamCommand_NullTeam() {
        assertThrows(ConstraintViolationException.class,
                () -> new SaveTeamCommand(null));
    }
}