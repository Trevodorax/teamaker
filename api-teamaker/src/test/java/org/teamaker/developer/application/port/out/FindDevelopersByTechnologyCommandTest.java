package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindDevelopersByTechnologyCommandTest {
    private static String validTechnologyId;

    @BeforeAll
    public static void setUp() {
        validTechnologyId = "validTechnologyId";
    }

    @Test
    public void testConstructorValidData() {
        FindDevelopersByTechnologyCommand command = new FindDevelopersByTechnologyCommand(validTechnologyId);
        assertEquals(validTechnologyId, command.technologyId());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(NullPointerException.class,
                () -> new FindDevelopersByTechnologyCommand(null));
    }
}
