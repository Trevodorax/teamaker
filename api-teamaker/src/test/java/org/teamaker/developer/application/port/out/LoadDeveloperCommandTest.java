package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoadDeveloperCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    public static void setUp() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    public void testConstructorValidData() {
        LoadDeveloperCommand command = new LoadDeveloperCommand(validDeveloperId);
        assertEquals(validDeveloperId, command.getDeveloperId());
    }

    @Test
    public void testConstructorEmptyDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LoadDeveloperCommand(null));
    }
}
