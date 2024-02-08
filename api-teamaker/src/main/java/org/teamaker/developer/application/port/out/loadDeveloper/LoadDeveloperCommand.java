package org.teamaker.developer.application.port.out.loadDeveloper;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class LoadDeveloperCommand extends SelfValidating<LoadDeveloperCommand> {
    @NotNull
    private final String developerId;

    public LoadDeveloperCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }
}
