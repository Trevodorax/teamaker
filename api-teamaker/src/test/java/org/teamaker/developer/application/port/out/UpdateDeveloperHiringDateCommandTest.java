package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

public class UpdateDeveloperHiringDateCommandTest {
    private static String validEmail;
    private static String invalidEmail;
    private static LocalDate validHiringDate;

    @BeforeAll
    public static void setUp() {
        validEmail = "john.doe@teamaker.com";
        invalidEmail = "john.doeteamaker.com";
        validHiringDate = LocalDate.of(2025, 1, 1);
    }

    @Test
    public void testConstructorValidData() {
        UpdateDeveloperHiringDateCommand command = new UpdateDeveloperHiringDateCommand(validEmail, validHiringDate);
        assertEquals(validEmail, command.email());
        assertEquals(validHiringDate, command.hiringDate());
    }

    @Test
    public void testConstructorEmptyEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperHiringDateCommand(null, validHiringDate));
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperHiringDateCommand(invalidEmail, validHiringDate));
    }

    @Test
    public void testConstructorEmptyHiringDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperHiringDateCommand(validEmail, null));
    }
}
