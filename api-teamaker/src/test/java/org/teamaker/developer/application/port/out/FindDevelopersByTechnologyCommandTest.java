package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.technology.domain.Technology;

import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindDevelopersByTechnologyCommandTest {
    private static Technology validTechnology;

    @BeforeAll
    public static void setUp() {
        validTechnology = new Technology("Technology Id", "Technology name");
    }

    @Test
    public void testConstructorValidData() {
        FindDevelopersByTechnologyCommand command = new FindDevelopersByTechnologyCommand(validTechnology);
        assertEquals(validTechnology, command.getTechnology());
    }

    @Test
    public void testConstructorEmptyTechnologyId() {
        assertThrows(ConstraintViolationException.class,
                () -> new FindDevelopersByTechnologyCommand(null));
    }
}
