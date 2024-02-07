package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.domain.Developer;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaveDeveloperCommandTest {
    private static Developer validDeveloper;

    @BeforeAll
    public static void setUp() {
        validDeveloper = new Developer("validDeveloperId", "John McClane", "john-mcclane@test.Com", LocalDate.now().minusDays(15), null);
    }

    @Test
    public void testConstructorValidData() {
        SaveDeveloperCommand command = new SaveDeveloperCommand(validDeveloper);
        assertEquals(validDeveloper, command.getDeveloper());
    }

    @Test
    public void testConstructorEmptyDeveloper() {
        assertThrows(ConstraintViolationException.class,
                () -> new SaveDeveloperCommand(null));
    }
}