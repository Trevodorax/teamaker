package org.teamaker.developer.application.port.in.getDeveloperSkills;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetDeveloperSkillsCommand extends SelfValidating<GetDeveloperSkillsCommand> {
    @NotNull
    private final String developerId;

    public GetDeveloperSkillsCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
