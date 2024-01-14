package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDeveloperInfo.GetDeveloperInfoCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetDeveloperCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    public static void setup() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    public void testConstructorValidData() {
        GetDeveloperInfoCommand command = new GetDeveloperInfoCommand(validDeveloperId);
        assertEquals(validDeveloperId, command.getDeveloperId());
    }

    @Test
    public void testConstructorEmptyDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetDeveloperInfoCommand(null));
    }
}
