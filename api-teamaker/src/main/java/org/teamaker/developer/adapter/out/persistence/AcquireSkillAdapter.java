package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillPort;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillResponse;

@Component
public class AcquireSkillAdapter implements AcquireSkillPort {
    private final DeveloperRepository developerRepository;

    public AcquireSkillAdapter(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public AcquireSkillResponse acquireSkill(AcquireSkillCommand command) {
        return null;
    }
}
