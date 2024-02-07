package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoRequest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDeveloperInfoRequestTest {
    private static String validFullName;
    private static String validEmail;

    @BeforeAll
    public static void setUp() {
        validFullName = "John McClane";
        validEmail = "john.maclane@gmail.com";
    }

    @Test
    public void testConstructorValidData() {
        UpdateDeveloperInfoRequest request = new UpdateDeveloperInfoRequest(validFullName, validEmail);
        assertEquals(validFullName, request.getFullName());
        assertEquals(validEmail, request.getEmail());
    }

    @Test
    public void testConstructorEmptyFullName() {
        UpdateDeveloperInfoRequest request = new UpdateDeveloperInfoRequest(null, validEmail);
        assertNull(request.getFullName());
        assertEquals(validEmail, request.getEmail());
    }

    @Test
    public void testConstructorEmptyEmail() {
        UpdateDeveloperInfoRequest request = new UpdateDeveloperInfoRequest(validFullName, null);
        assertEquals(validFullName, request.getFullName());
        assertNull(request.getEmail());
    }

    @Test
    public void testConstructorEmptyFullNameAndEmail() {
        UpdateDeveloperInfoRequest request = new UpdateDeveloperInfoRequest(null, null);
        assertNull(request.getFullName());
        assertNull(request.getEmail());
    }

    @Test
    public void testConstructorBadEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> new UpdateDeveloperInfoRequest(validFullName, "badEmail"));
    }
}