package org.teamaker.developer.application.port.in.forgetSkill;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class ForgetSkillCommand extends SelfValidating<ForgetSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String technologyId;

    public ForgetSkillCommand(String developerId, String technologyId) {
        this.developerId = developerId;
        this.technologyId = technologyId;
        this.validateSelf();
    }

}
