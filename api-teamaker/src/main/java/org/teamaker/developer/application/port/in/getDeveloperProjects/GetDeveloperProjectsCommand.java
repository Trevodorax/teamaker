package org.teamaker.developer.application.port.in.getDeveloperProjects;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class GetDeveloperProjectsCommand extends SelfValidating<GetDeveloperProjectsCommand> {
    @NotNull
    private final String developerId;

    public GetDeveloperProjectsCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }
}
