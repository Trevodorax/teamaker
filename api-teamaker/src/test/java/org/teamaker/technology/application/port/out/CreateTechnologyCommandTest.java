package org.teamaker.technology.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyCommand;

import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateTechnologyCommandTest {
    private static String validName;

    @BeforeAll
    public static void setUp() {
        validName = "validName";
    }

    @Test
    public void testConstructorValidData() {
        CreateTechnologyCommand command = new CreateTechnologyCommand(validName);
        assertEquals(validName, command.getName());
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class,
                () -> new CreateTechnologyCommand(null));
    }
}
