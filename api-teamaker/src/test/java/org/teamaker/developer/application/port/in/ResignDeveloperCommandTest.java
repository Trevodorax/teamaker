package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResignDeveloperCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    void testConstructor() {
        ResignDeveloperCommand resignDeveloperCommand = new ResignDeveloperCommand(validDeveloperId);
        assertEquals(validDeveloperId, resignDeveloperCommand.getDeveloperId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(null));
    }
}