package org.teamaker.team.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase.TreatTeamChangeRequestRequest;
import org.teamaker.team.domain.TeamRequestStatus;
import org.teamaker.team.domain.TreatTeamStatus;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatTeamChangeRequestRequestTest {
    private static TreatTeamStatus status;

    @BeforeAll
    static void setUp() {
        status = TreatTeamStatus.APPROVED;
    }

    @Test
    public void testTreatTeamChangeRequestRequest() {
        TreatTeamChangeRequestRequest treatTeamChangeRequestRequest = new TreatTeamChangeRequestRequest(status);
        assertEquals(status, treatTeamChangeRequestRequest.getStatus());
    }

    @Test
    public void testTreatTeamChangeRequestRequestNullStatus() {
        assertThrows(ConstraintViolationException.class,
                () -> new TreatTeamChangeRequestRequest(null));
    }

}