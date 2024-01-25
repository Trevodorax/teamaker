package org.teamaker.developer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.forgetSkill.ForgetSkillCommand;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillCommand;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillPort;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ForgetSkillServiceTest {
    private static RemoveSkillPort removeSkillPortMock;
    private static ForgetSkillService forgetSkillService;

    @BeforeEach
    public void setUp() {
        removeSkillPortMock = mock(RemoveSkillPort.class);
        forgetSkillService = new ForgetSkillService(removeSkillPortMock);
    }

    @Test
    public void forgetSkillSuccess() {
        assertEquals("Skill successfully forgotten", forgetSkillService.forgetSkill(new ForgetSkillCommand("developerId", "skillId")));
    }

    @Test
    public void forgetSkillError() {
        doThrow(new NoSuchElementException("errorMessage")).when(removeSkillPortMock).removeSkill(any(RemoveSkillCommand.class));
        assertEquals("errorMessage", forgetSkillService.forgetSkill(new ForgetSkillCommand("developerId", "skillId")));
    }
}
