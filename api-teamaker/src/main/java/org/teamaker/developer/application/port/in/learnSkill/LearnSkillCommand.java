package org.teamaker.developer.application.port.in.learnSkill;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class LearnSkillCommand extends SelfValidating<LearnSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String technologyId;

    public LearnSkillCommand(String developerId, String skillId) {
        this.developerId = developerId;
        this.technologyId = skillId;
        this.validateSelf();
    }
}
