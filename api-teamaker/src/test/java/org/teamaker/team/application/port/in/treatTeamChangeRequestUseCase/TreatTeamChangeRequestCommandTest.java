package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.team.domain.TreatTeamStatus;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class TreatTeamChangeRequestCommandTest {
    private static String validTeamChangeRequestId;
    private static TreatTeamStatus validStatus;

    @BeforeAll
    public static void setUp() {
        validTeamChangeRequestId = "validTeamChangeRequestId";
        validStatus = TreatTeamStatus.approved;
    }

    @Test
    public void testConstructorValidData() {
        TreatTeamChangeRequestCommand command = new TreatTeamChangeRequestCommand(validTeamChangeRequestId, validStatus);
        assertEquals(validTeamChangeRequestId, command.getTeamChangeRequestId());
        assertEquals(validStatus, command.getStatus());
    }

    @Test
    public void testConstructorEmptyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new TreatTeamChangeRequestCommand(null, validStatus));
    }

    @Test
    public void testConstructorEmptyStatus() {
        assertThrows(ConstraintViolationException.class,
                () -> new TreatTeamChangeRequestCommand(validTeamChangeRequestId, null));
    }
}