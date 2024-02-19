package org.teamaker.team.application.port.out.createTeamChangeRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateTeamChangeRequestCommandTest {
    private static String developerId;
    private static String requestedProjectId;
    private static String toProjectId;

    @BeforeAll
    public static void setUp() {
        developerId = "developerId";
        requestedProjectId = "requestedProjectId";
        toProjectId = "toProjectId";
    }

    @Test
    public void testConstructorValidData() {
        CreateTeamChangeRequestCommand command = new CreateTeamChangeRequestCommand(developerId, requestedProjectId, toProjectId);
        assertEquals(developerId, command.getDeveloperId());
        assertEquals(requestedProjectId, command.getRequestedProjectId());
    }

    @Test
    public void testConstructorEmptyDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateTeamChangeRequestCommand(null, requestedProjectId, toProjectId));
    }

    @Test
    public void testConstructorEmptyProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateTeamChangeRequestCommand(developerId, null, toProjectId));
    }
}