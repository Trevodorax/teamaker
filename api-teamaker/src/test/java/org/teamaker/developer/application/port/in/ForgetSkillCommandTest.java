package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.forgetSkill.ForgetSkillCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ForgetSkillCommandTest {
    private static String validDeveloperId;
    private static String validSkillId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validSkillId = "validSkillId";
    }

    @Test
    void testConstructorValidData() {
        ForgetSkillCommand forgetSkillCommand = new ForgetSkillCommand(validDeveloperId, validSkillId);
        assertEquals(validDeveloperId, forgetSkillCommand.getDeveloperId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new ForgetSkillCommand(null, validSkillId));
    }

    @Test
    void testConstructorNullSkillId() {
        assertThrows(ConstraintViolationException.class,
                () -> new ForgetSkillCommand(validDeveloperId, null));
    }
}