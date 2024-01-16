package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveSkillCommandTest {
    private static String validDeveloperId;
    private static String validSkillId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validSkillId = "validSkillId";
    }

    @Test
    void testConstructorValidData() {
        RemoveSkillCommand removeSkillCommand = new RemoveSkillCommand(validDeveloperId, validSkillId);
        assertEquals(validDeveloperId, removeSkillCommand.getDeveloperId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new RemoveSkillCommand(null, validSkillId));
    }

    @Test
    void testConstructorNullSkillId() {
        assertThrows(ConstraintViolationException.class,
                () -> new RemoveSkillCommand(validDeveloperId, null));
    }
}