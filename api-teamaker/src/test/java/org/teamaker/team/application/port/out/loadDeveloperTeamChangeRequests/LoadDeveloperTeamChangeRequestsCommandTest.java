package org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoadDeveloperTeamChangeRequestsCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    public static void setUp() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    public void testConstructorValidData() {
        LoadDeveloperTeamChangeRequestsCommand command = new LoadDeveloperTeamChangeRequestsCommand(validDeveloperId);
        assertEquals(validDeveloperId, command.getDeveloperId());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LoadDeveloperTeamChangeRequestsCommand(null));
    }
}