package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

import org.teamaker.project.domain.Priority;

class SubmitProjectCommandTest {
    private static String validName = "Project Name";
    private static String validDescription = "Project Description";
    private static Priority validPriority = Priority.CRITICAL;
    private static LocalDate validStartDate = LocalDate.now().plusDays(1);
    private static LocalDate validEndDate = validStartDate.plusDays(5);
    private static ArrayList<UUID> validTechnologies = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        validName = "Project Name";
        validDescription = "Project Description";
        validPriority = Priority.CRITICAL;
        validStartDate = LocalDate.now().plusDays(1);
        validEndDate = validStartDate.plusDays(5);
        validTechnologies = new ArrayList<>();
        validTechnologies.add(UUID.randomUUID());
    }

    @Test
    public void testConstructorValidData() {
        SubmitProjectCommand command = new SubmitProjectCommand(validName, validDescription, validPriority, validStartDate, validEndDate, validTechnologies);

        assertEquals(validName, command.getName());
        assertEquals(validDescription, command.getDescription());
        assertEquals(validPriority, command.getPriority());
        assertEquals(validStartDate, command.getStartDate());
        assertEquals(validEndDate, command.getEndDate());
        assertEquals(validTechnologies, command.getTechnologies());
    }

    @Test
    public void testConstructorPastStartDate() {
        assertThrows(ConstraintViolationException.class, () -> {
            new SubmitProjectCommand(validName, validDescription, validPriority, LocalDate.now().minusDays(1), validEndDate, validTechnologies);
        });
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class, () -> {
            new SubmitProjectCommand(null, validDescription, validPriority, validStartDate, validEndDate, validTechnologies);
        });
    }

    @Test
    public void testConstructorEmptyPriority() {
        assertThrows(ConstraintViolationException.class, () -> {
            new SubmitProjectCommand(validName, validDescription, null, validStartDate, validEndDate, validTechnologies);
        });
    }

    @Test
    public void testConstructorEndDateBeforeStartDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubmitProjectCommand(validName, validDescription, validPriority, validStartDate, validStartDate.minusDays(1), validTechnologies);
        });
    }
}