package org.teamaker.developer.application.port.out.acquireSkill;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AcquireSkillCommand extends SelfValidating<AcquireSkillCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String skillId;

    public AcquireSkillCommand(String developerId, String skillId) {
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
