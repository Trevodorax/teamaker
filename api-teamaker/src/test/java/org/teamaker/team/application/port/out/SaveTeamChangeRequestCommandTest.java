package org.teamaker.team.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestCommand;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SaveTeamChangeRequestCommandTest {
    private static TeamChangeRequest validTeamChangeRequest;

    @BeforeAll
    public static void setUp() {
        validTeamChangeRequest = new TeamChangeRequest("teamId", "developerId", "projectId", "teamChangeRequestType", TeamRequestStatus.PENDING, LocalDate.now());
    }

    @Test
    public void testSaveTeamChangeRequestCommand_Success() {
        SaveTeamChangeRequestCommand saveTeamChangeRequestCommand = new SaveTeamChangeRequestCommand(validTeamChangeRequest);
        assertEquals(validTeamChangeRequest, saveTeamChangeRequestCommand.getTeamChangeRequest());
    }

    @Test
    public void testSaveTeamChangeRequestCommand_NullTeamChangeRequest() {
        assertThrows(ConstraintViolationException.class,
                () -> new SaveTeamChangeRequestCommand(null));
    }

}