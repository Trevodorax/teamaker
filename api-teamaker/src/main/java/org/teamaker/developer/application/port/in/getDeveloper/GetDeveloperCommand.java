package org.teamaker.developer.application.port.in.getDeveloper;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class GetDeveloperCommand extends SelfValidating<GetDeveloperCommand> {
    @NotNull
    private final String developerId;

    public GetDeveloperCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }

}
