package org.teamaker.developer.application.port.out.loadDeveloperSkills;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class LoadDeveloperSkillsCommand extends SelfValidating<LoadDeveloperSkillsCommand> {
    @NotNull
    private final String developerId;

    public LoadDeveloperSkillsCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }
}
