package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetDeveloperProjectsCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    public static void setUp() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    public void testConstructorValidData() {
        GetDeveloperProjectsCommand command = new GetDeveloperProjectsCommand(validDeveloperId);
        assertEquals(validDeveloperId, command.getDeveloperId());
    }

    @Test
    public void testConstructorEmptyDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetDeveloperProjectsCommand(null));
    }

}