package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.treatProject.TreatProjectCommand;
import org.teamaker.project.domain.ProjectStatus;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreatProjectCommandTest {
    private static String validProjectId;
    private static ProjectStatus validStatus;

    @BeforeAll
    public static void setUp() {
        validProjectId = "validProjectId";
        validStatus = ProjectStatus.PENDING;
    }

    @Test
    public void testConstructorValidData() {
        TreatProjectCommand command = new TreatProjectCommand(validProjectId, validStatus);
        assertEquals(validProjectId, command.getProjectId());
        assertEquals(validStatus, command.getStatus());
    }

    @Test
    public void testConstructorEmptyProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new TreatProjectCommand(null, validStatus));
    }

    @Test
    public void testConstructorEmptyStatus() {
        assertThrows(ConstraintViolationException.class,
                () -> new TreatProjectCommand(validProjectId, null));
    }
}
