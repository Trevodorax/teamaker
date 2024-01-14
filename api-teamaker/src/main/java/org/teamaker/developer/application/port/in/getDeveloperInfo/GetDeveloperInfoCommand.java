package org.teamaker.developer.application.port.in.getDeveloperInfo;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetDeveloperInfoCommand extends SelfValidating<GetDeveloperInfoCommand> {
    @NotNull
    private final String developerId;

    public GetDeveloperInfoCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
