package org.teamaker.developer.application.port.out.acquireSkill;

import java.util.NoSuchElementException;

public interface AcquireSkillPort {
    AcquireSkillResponse acquireSkill(AcquireSkillCommand command) throws NoSuchElementException, IllegalArgumentException;
}
