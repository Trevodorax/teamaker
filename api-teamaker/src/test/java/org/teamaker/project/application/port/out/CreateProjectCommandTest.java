package org.teamaker.project.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.domain.Priority;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateProjectCommandTest {
    private static String validName = "Project Name";
    private static String validDescription = "Project Description";
    private static Priority validPriority = Priority.CRITICAL;
    private static LocalDate validStartDate = LocalDate.now().plusDays(1);
    private static LocalDate validEndDate = validStartDate.plusDays(5);

    @BeforeAll
    public static void setUp() {
        validName = "Project Name";
        validDescription = "Project Description";
        validPriority = Priority.CRITICAL;
        validStartDate = LocalDate.now().plusDays(1);
        validEndDate = validStartDate.plusDays(5);
    }

    @Test
    public void testConstructorValidData() {
        CreateProjectCommand command = new CreateProjectCommand(validName, validDescription, validPriority, validStartDate, validEndDate);

        assertEquals(validName, command.getName());
        assertEquals(validDescription, command.getDescription());
        assertEquals(validPriority, command.getPriority());
        assertEquals(validStartDate, command.getStartDate());
        assertEquals(validEndDate, command.getEndDate());
    }

    @Test
    public void testConstructorPastStartDate() {
        assertThrows(ConstraintViolationException.class, () -> {
            new CreateProjectCommand(validName, validDescription, validPriority, LocalDate.now().minusDays(1), validEndDate);
        });
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class, () -> {
            new CreateProjectCommand(null, validDescription, validPriority, validStartDate, validEndDate);
        });
    }

    @Test
    public void testConstructorEmptyPriority() {
        assertThrows(ConstraintViolationException.class, () -> {
            new CreateProjectCommand(validName, validDescription, null, validStartDate, validEndDate);
        });
    }

    @Test
    public void testConstructorEndDateBeforeStartDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CreateProjectCommand(validName, validDescription, validPriority, validStartDate, validStartDate.minusDays(1));
        });
    }

}