package org.teamaker.developer.application.port.in.learnSkill;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LearnSkillCommand extends SelfValidating<LearnSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String skillId;

    public LearnSkillCommand(String developerId, String skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }

    public String getSkillId() {
        return skillId;
    }
}
