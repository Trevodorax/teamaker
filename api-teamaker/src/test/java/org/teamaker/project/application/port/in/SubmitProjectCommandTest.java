package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.domain.Priority;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SubmitProjectCommandTest {
    private static String name = "Project Name";
    private static String description = "Project Description";
    private static Priority priority = Priority.CRITICAL;
    private static LocalDate startDate = LocalDate.now().plusDays(1);
    private static LocalDate endDate = startDate.plusDays(5);
    private static ArrayList<UUID> technologies = new ArrayList<>();
    @BeforeAll
    public static void setUp() {
        name = "Project Name";
        description = "Project Description";
        priority = Priority.CRITICAL;
        startDate = LocalDate.now().plusDays(1);
        endDate = startDate.plusDays(5);
        technologies = new ArrayList<>();
        technologies.add(UUID.randomUUID());
    }
    @Test
    public void testConstructorValidData() {
        SubmitProjectCommand command = new SubmitProjectCommand(name, description, priority, startDate, endDate, technologies);

        assertEquals(name, command.getName());
        assertEquals(description, command.getDescription());
        assertEquals(priority, command.getPriority());
        assertEquals(startDate, command.getStartDate());
        assertEquals(endDate, command.getEndDate());
        assertEquals(technologies, command.getTechnologies());
    }

    @Test
    public void testConstructorPastStartDate() {
        assertThrows(ConstraintViolationException.class, () -> {
            new SubmitProjectCommand(name, description, priority, LocalDate.now().minusDays(1), endDate, technologies);
        });
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class, () -> {
            new SubmitProjectCommand(null, description, priority, startDate, endDate, technologies);
        });
    }

    @Test
    public void testConstructorEmptyPriority() {
        assertThrows(ConstraintViolationException.class, () -> {
            new SubmitProjectCommand(name, description, null, startDate, endDate, technologies);
        });
    }

    @Test
    public void testConstructorEndDateBeforeStartDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubmitProjectCommand(name, description, priority, startDate, startDate.minusDays(1), technologies);
        });
    }
}