package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class LoadDeveloperSkillsCommandTest {
    private static String validDeveloperId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
    }

    @Test
    void testGetDeveloperId() {
        LoadDeveloperSkillsCommand loadDeveloperSkillsCommand = new LoadDeveloperSkillsCommand(validDeveloperId);
        assertEquals(validDeveloperId, loadDeveloperSkillsCommand.getDeveloperId());
    }

    @Test
    void testGetDeveloperIdNull() {
        assertThrows(ConstraintViolationException.class,
                () -> new LoadDeveloperSkillsCommand(null));
    }

}