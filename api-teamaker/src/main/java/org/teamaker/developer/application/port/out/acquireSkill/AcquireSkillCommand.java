package org.teamaker.developer.application.port.out.acquireSkill;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class AcquireSkillCommand extends SelfValidating<AcquireSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String technologyId;

    public AcquireSkillCommand(String developerId, String technologyId) {
        this.developerId = developerId;
        this.technologyId = technologyId;
        this.validateSelf();
    }

}
