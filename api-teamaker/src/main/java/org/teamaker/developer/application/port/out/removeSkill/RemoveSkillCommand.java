package org.teamaker.developer.application.port.out.removeSkill;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class RemoveSkillCommand extends SelfValidating<RemoveSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String technologyId;

    public RemoveSkillCommand(String developerId, String technologyId) {
        this.developerId = developerId;
        this.technologyId = technologyId;
        this.validateSelf();
    }

}
