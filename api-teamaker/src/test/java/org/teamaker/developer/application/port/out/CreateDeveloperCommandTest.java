package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

public class CreateDeveloperCommandTest {
    private static String validFullName;
    private static String validEmail;
    private static String invalidEmail;
    private static LocalDate validHiringDate;
    private static LocalDate pastHiringDate;

    @BeforeAll
    public static void setUp() {
        validFullName = "John DOE";
        validEmail = "john.doe@teamaker.com";
        invalidEmail = "john.doeteamaker.com";
        validHiringDate = LocalDate.of(2025, 1, 1);
        pastHiringDate = LocalDate.of(2022, 1, 1);
    }

    @Test
    public void testConstructorValidData() {
        CreateDeveloperCommand command = new CreateDeveloperCommand(validFullName, validEmail, validHiringDate);
        assertEquals(validFullName, command.getFullName());
        assertEquals(validEmail, command.getEmail());
        assertEquals(validHiringDate, command.getHiringDate());
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateDeveloperCommand(null, validEmail, validHiringDate));
    }

    @Test
    public void testConstructorEmptyEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateDeveloperCommand(validFullName, null, validHiringDate));
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateDeveloperCommand(validFullName, invalidEmail, validHiringDate));
    }

    @Test
    public void testConstructorEmptyHiringDate() {
        CreateDeveloperCommand command = new CreateDeveloperCommand(validFullName, validEmail, null);
        LocalDate expectedDate = LocalDate.now();
        assertEquals(expectedDate, command.getHiringDate());
    }

    @Test
    public void testConstructorPastHiringDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateDeveloperCommand(validFullName, validEmail, pastHiringDate));
    }
}
