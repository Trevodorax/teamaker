package org.teamaker.developer.application.port.out.loadDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadDeveloperCommand extends SelfValidating<LoadDeveloperCommand> {
    @NotNull
    private final String developerId;

    public LoadDeveloperCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
