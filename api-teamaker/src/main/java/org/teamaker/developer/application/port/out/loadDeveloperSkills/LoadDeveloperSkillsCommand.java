package org.teamaker.developer.application.port.out.loadDeveloperSkills;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadDeveloperSkillsCommand extends SelfValidating<LoadDeveloperSkillsCommand> {
    @NotNull
    private final String developerId;

    public LoadDeveloperSkillsCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
