package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

public class ResignDeveloperCommandTest {
    private static String validEmail;
    private static String invalidEmail;
    private static LocalDate validResignationDate;

    @BeforeAll
    public static void setUp() {
        validEmail = "john.doe@teamaker.com";
        invalidEmail = "john.doeteamaker.com";
        validResignationDate = LocalDate.of(2024, 6, 21);
    }

    @Test
    public void testConstructorValidData() {
        ResignDeveloperCommand command = new ResignDeveloperCommand(validEmail, validResignationDate);
        assertEquals(validEmail, command.getEmail());
        assertEquals(validResignationDate, command.getResignationDate());
    }

    @Test
    public void testConstructorEmptyEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(null, validResignationDate));
    }

    @Test
    public void testConstructorEmptyResignationDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(validEmail, null));
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(invalidEmail, validResignationDate));
    }
}
