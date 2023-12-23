package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.ConstraintViolationException;


public class HireDeveloperCommandTest {
    private static String validFullName;
    private static String validEmail;
    private static String invalidEmail;

    @BeforeAll
    public static void setUp() {
        validFullName = "John DOE";
        validEmail = "john.doe@teamaker.com";
        invalidEmail = "john.doeteamaker.com";
    }

    @Test
    public void testConstructorValidData() {
        HireDeveloperCommand command = new HireDeveloperCommand(validFullName, validEmail);
        assertEquals(validFullName, command.getFullName());
        assertEquals(validEmail, command.getEmail());
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class,
                () -> new HireDeveloperCommand(null, validEmail));
    }

    @Test
    public void testConstructorEmptyEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new HireDeveloperCommand(validFullName, null));
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new HireDeveloperCommand(validFullName, invalidEmail));
    }
}
