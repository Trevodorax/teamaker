package org.teamaker.technology.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetTechnologyCommandTest {
    private static String validId;

    @BeforeAll
    public static void setUp() {
        validId = "validId";
}
    @Test
    public void testConstructorValidData() {
        GetTechnologyCommand command = new GetTechnologyCommand(validId);
        assertEquals(validId, command.getTechnologyId());
    }

    @Test
    public void testConstructorEmptyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetTechnologyCommand(null));
    }
}
