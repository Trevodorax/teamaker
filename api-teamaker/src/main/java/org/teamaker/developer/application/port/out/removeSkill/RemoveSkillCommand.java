package org.teamaker.developer.application.port.out.removeSkill;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RemoveSkillCommand extends SelfValidating<RemoveSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String skillId;

    public RemoveSkillCommand(String developerId, String skillId) {
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
