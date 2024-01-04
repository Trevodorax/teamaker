package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HireDeveloperCommandTest {
    private static Validator validator;
    private static String validFullName;
    private static String validEmail;
    private static String invalidEmail;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        validFullName = "John DOE";
        validEmail = "john.doe@teamaker.com";
        invalidEmail = "john.doeteamaker.com";
    }

    @Test
    public void testConstructorValidData() {
        HireDeveloperCommand command = new HireDeveloperCommand(validFullName, validEmail);
        assertEquals(validFullName, command.fullName());
        assertEquals(validEmail, command.email());
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(NullPointerException.class,
                () -> new HireDeveloperCommand(null, validEmail));
    }

    @Test
    public void testConstructorEmptyEmail() {
        assertThrows(NullPointerException.class,
                () -> new HireDeveloperCommand(validFullName, null));
    }

    @Test
    public void testConstructorInvalidEmail() throws Exception {
        HireDeveloperCommand command = new HireDeveloperCommand(validFullName, invalidEmail);
        Set<ConstraintViolation<HireDeveloperCommand>> violations = validator.validate(command);

        assertFalse(violations.isEmpty());
    }
}
