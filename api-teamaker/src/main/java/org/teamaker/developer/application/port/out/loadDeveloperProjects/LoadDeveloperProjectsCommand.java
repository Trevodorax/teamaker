package org.teamaker.developer.application.port.out.loadDeveloperProjects;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadDeveloperProjectsCommand extends SelfValidating<LoadDeveloperProjectsCommand> {
    @NotNull
    private final String developerId;

    public LoadDeveloperProjectsCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
