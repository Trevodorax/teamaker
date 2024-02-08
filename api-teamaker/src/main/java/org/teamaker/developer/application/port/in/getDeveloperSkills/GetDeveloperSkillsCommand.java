package org.teamaker.developer.application.port.in.getDeveloperSkills;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class GetDeveloperSkillsCommand extends SelfValidating<GetDeveloperSkillsCommand> {
    @NotNull
    private final String developerId;

    public GetDeveloperSkillsCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }
}
