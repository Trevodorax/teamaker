package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

public class UpdateDeveloperResignationDateCommandTest {
    private static String validEmail;
    private static String invalidEmail;
    private static LocalDate validResignationDate;

    @BeforeAll
    public static void setUp() {
        validEmail = "john.doe@teamaker.com";
        invalidEmail = "john.doeteamaker.com";
        validResignationDate = LocalDate.of(2025, 1, 1);
    }

    @Test
    public void testConstructorValidData() {
        UpdateDeveloperResignationDateCommand command = new UpdateDeveloperResignationDateCommand(validEmail, validResignationDate);
        assertEquals(validEmail, command.email());
        assertEquals(validResignationDate, command.resignationDate());
    }

    @Test
    public void testConstructorEmptyEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperResignationDateCommand(null, validResignationDate));
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperResignationDateCommand(invalidEmail, validResignationDate));
    }

    @Test
    public void testConstructorEmptyHiringDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperResignationDateCommand(validEmail, null));
    }
}
