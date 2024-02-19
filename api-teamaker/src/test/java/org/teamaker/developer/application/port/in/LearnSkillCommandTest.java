package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LearnSkillCommandTest {
    private static String validDeveloperId;
    private static String validSkillId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validSkillId = "validSkillId";
    }

    @Test
    void testConstructorValidData() {
        LearnSkillCommand learnSkillCommand = new LearnSkillCommand(validDeveloperId, validSkillId);
        assertEquals(validDeveloperId, learnSkillCommand.getDeveloperId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LearnSkillCommand(null, validSkillId));
    }

    @Test
    void testConstructorNullSkillId() {
        assertThrows(ConstraintViolationException.class,
                () -> new LearnSkillCommand(validDeveloperId, null));
    }
}