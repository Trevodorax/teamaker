package org.teamaker.developer.application.port.out.removeSkill;

import java.util.NoSuchElementException;

public interface RemoveSkillPort {
    void removeSkill(RemoveSkillCommand command) throws NoSuchElementException;
}
