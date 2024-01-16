package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetDeveloperSkillsCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    void testGetDeveloperId() {
        GetDeveloperSkillsCommand getDeveloperSkillsCommand = new GetDeveloperSkillsCommand(validDeveloperId);
        assertEquals(validDeveloperId, getDeveloperSkillsCommand.getDeveloperId());
    }

    @Test
    void testGetDeveloperIdNull() {
        assertThrows(ConstraintViolationException.class,
                () -> new GetDeveloperSkillsCommand(null));
    }

}