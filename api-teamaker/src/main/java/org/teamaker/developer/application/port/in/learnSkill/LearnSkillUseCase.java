package org.teamaker.developer.application.port.in.learnSkill;

import org.teamaker.developer.domain.dto.LearnSkillResponse;

public interface LearnSkillUseCase {
    LearnSkillResponse learnSkill(LearnSkillCommand command);
}
