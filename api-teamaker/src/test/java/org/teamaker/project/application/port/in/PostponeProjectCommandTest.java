package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectCommand;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PostponeProjectCommandTest {
    private static String validProjectId;
    private static LocalDate validNewStartDate;
    private static LocalDate validNewEndDate;

    @BeforeAll
    public static void setUp() {
        validProjectId = "validProjectId";
        validNewStartDate = LocalDate.now().plusDays(1);
        validNewEndDate = LocalDate.now().plusDays(2);
    }

    @Test
    public void testConstructorValidData() {
        PostponeProjectCommand command = new PostponeProjectCommand(validProjectId, validNewStartDate, validNewEndDate);
        assertEquals(validProjectId, command.getProjectId());
        assertEquals(validNewStartDate, command.getNewStartDate());
        assertEquals(validNewEndDate, command.getNewEndDate());
    }

    @Test
    public void testConstructorEmptyProjectId() {
        assertThrows(ConstraintViolationException.class,
                () -> new PostponeProjectCommand(null, validNewStartDate, validNewEndDate));
    }

    @Test
    public void testConstructorEmptyNewStartDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new PostponeProjectCommand(validProjectId, null, validNewEndDate));
    }

    @Test
    public void testConstructorEmptyNewEndDate() {
        PostponeProjectCommand command = new PostponeProjectCommand(validProjectId, validNewStartDate, null);
        assertEquals(validProjectId, command.getProjectId());
        assertEquals(validNewStartDate, command.getNewStartDate());
        assertNull(command.getNewEndDate());
    }

    @Test
    public void testConstructorNewStartDateAfterNewEndDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new PostponeProjectCommand(validProjectId, validNewEndDate, validNewStartDate));
    }
}