package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetDevelopersByTechnologyCommandTest {
    private static String validTechnologyId;

    @BeforeAll
    public static void setUp() {
        validTechnologyId = "validTechnologyId";
    }

    @Test
    public void testConstructorValidData() {
        GetDevelopersByTechnologyCommand command = new GetDevelopersByTechnologyCommand(validTechnologyId);
        assertEquals(validTechnologyId, command.getTechnologyId());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetDevelopersByTechnologyCommand(null));
    }
}
