package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillCommand;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillPort;

import java.util.NoSuchElementException;

@Component
public class RemoveSkillAdapter implements RemoveSkillPort {
    @Override
    public void removeSkill(RemoveSkillCommand command) throws NoSuchElementException {
    }
}
