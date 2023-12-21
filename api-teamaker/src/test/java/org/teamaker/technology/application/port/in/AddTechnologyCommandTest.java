package org.teamaker.technology.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTechnologyCommandTest {
    private static String validName = "Technology Name";

    @BeforeAll
    public static void setUp() {
        validName = "validName";
    }

    @Test
    public void testConstructorValidData() {
        AddTechnologyCommand command = new AddTechnologyCommand(validName);

        System.out.println(command.getName());
        assertEquals(validName, command.getName());
    }

}
