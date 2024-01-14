package org.teamaker.developer.application.port.out.loadDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class loadDeveloperCommand extends SelfValidating<loadDeveloperCommand> {
    @NotNull
    private final String developerId;

    public loadDeveloperCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
