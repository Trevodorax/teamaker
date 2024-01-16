package org.teamaker.developer.application.port.out;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AcquireSkillCommandTest {
    private static String validDeveloperId;
    private static String validSkillId;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validSkillId = "validSkillId";
    }

    @Test
    void testConstructor() {
        AcquireSkillCommand acquireSkillCommand = new AcquireSkillCommand(validDeveloperId, validSkillId);
        assertEquals(validDeveloperId, acquireSkillCommand.getDeveloperId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new AcquireSkillCommand(null, validSkillId));
    }

    @Test
    void testConstructorNullSkillId() {
        assertThrows(ConstraintViolationException.class,
                () -> new AcquireSkillCommand(validDeveloperId, null));
    }
}