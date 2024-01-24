package org.teamaker.developer.application.port.in.getDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetDeveloperCommand extends SelfValidating<GetDeveloperCommand> {
    @NotNull
    private final String id;

    public GetDeveloperCommand(String id) {
        this.id = id;

        this.validateSelf();
    }

    public String getId() {
        return id;
    }
}
