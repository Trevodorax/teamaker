package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDeveloperInfoCommandTest {
    private static String validDeveloperId;
    private static String validFullName;
    private static String validEmail;

    @BeforeAll
    public static void setUp() {
        validDeveloperId = "validDeveloperId";
        validFullName = "John McClane";
        validEmail = "john-mcclane@test.Com";
    }

    @Test
    public void testConstructorValidData() {
        UpdateDeveloperInfoCommand command = new UpdateDeveloperInfoCommand(validDeveloperId, validFullName, validEmail);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertEquals(validFullName, command.getFullName());
        assertEquals(validEmail, command.getEmail());
    }

    @Test
    public void testConstructorEmptyDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperInfoCommand(null, validFullName, validEmail));
    }

    @Test
    public void testConstructorEmptyFullName() {
        UpdateDeveloperInfoCommand command = new UpdateDeveloperInfoCommand(validDeveloperId, null, validEmail);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertNull(command.getFullName());
        assertEquals(validEmail, command.getEmail());
    }

    @Test
    public void testConstructorEmptyEmail() {
        UpdateDeveloperInfoCommand command = new UpdateDeveloperInfoCommand(validDeveloperId, validFullName, null);
        assertEquals(validDeveloperId, command.getDeveloperId());
        assertEquals(validFullName, command.getFullName());
        assertNull(command.getEmail());
    }

    @Test
    public void testConstructorBadEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperInfoCommand(validDeveloperId, validFullName, "badEmail"));
    }
}