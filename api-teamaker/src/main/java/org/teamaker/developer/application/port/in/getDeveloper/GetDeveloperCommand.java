package org.teamaker.developer.application.port.in.getDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetDeveloperCommand extends SelfValidating<GetDeveloperCommand> {
    @NotNull
    private final String developerId;

    public GetDeveloperCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
