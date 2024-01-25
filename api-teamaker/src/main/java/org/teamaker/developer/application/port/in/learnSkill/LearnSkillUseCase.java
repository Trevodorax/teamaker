package org.teamaker.developer.application.port.in.learnSkill;

public interface LearnSkillUseCase {
    LearnSkillResponse.Response learnSkill(LearnSkillCommand command);
}
