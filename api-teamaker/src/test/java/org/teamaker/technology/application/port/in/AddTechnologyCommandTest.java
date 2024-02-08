package org.teamaker.technology.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTechnologyCommandTest {
    private static String validName;

    @BeforeAll
    public static void setUp() {
        validName = "validName";
    }

    @Test
    public void testConstructorValidData() {
        AddTechnologyCommand command = new AddTechnologyCommand(validName);
        assertEquals(validName, command.getName());
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(ConstraintViolationException.class,
                () -> new AddTechnologyCommand(null));
    }
}
