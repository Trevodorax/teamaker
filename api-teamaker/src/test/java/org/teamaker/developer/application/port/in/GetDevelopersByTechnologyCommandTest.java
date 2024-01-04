package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetDevelopersByTechnologyCommandTest {
    private static String validTechnologyGuid;

    @BeforeAll
    public static void setUp() {
        validTechnologyGuid = "validTechnologyGuid";
    }

    @Test
    public void testConstructorValidData() {
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(validTechnologyGuid);
        assertEquals(validTechnologyGuid, command.getTechnology());
    }

    @Test
    public void testConstructorEmptyTechnologyGuid() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetDevelopersByTechnologyCommand(null));
    }
}
