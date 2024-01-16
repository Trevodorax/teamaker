package org.teamaker.developer.application.port.in.forgetSkill;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class ForgetSkillCommand extends SelfValidating<ForgetSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String skillId;

    public ForgetSkillCommand(String developerId, String skillId) {
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
