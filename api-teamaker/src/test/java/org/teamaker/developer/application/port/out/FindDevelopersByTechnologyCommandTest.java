package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindDevelopersByTechnologyCommandTest {
    private static String validTechnologyGuid;

    @BeforeAll
    public static void setUp() {
        validTechnologyGuid = "validTechnologyGuid";
    }

    @Test
    public void testConstructorValidData() {
        FindDevelopersByTechnologyCommand command = new FindDevelopersByTechnologyCommand(validTechnologyGuid);
        assertEquals(validTechnologyGuid, command.getTechnology());
    }

    @Test
    public void testConstructorEmptyTechnologyGuid() {
        assertThrows(ConstraintViolationException.class,
                () -> new FindDevelopersByTechnologyCommand(null));
    }
}
