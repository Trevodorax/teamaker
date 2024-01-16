package org.teamaker.developer.application.port.out.saveDeveloper;

import org.teamaker.developer.domain.Developer;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class SaveDeveloperCommand extends SelfValidating<SaveDeveloperCommand> {
    @NotNull
    private final Developer developer;

    public SaveDeveloperCommand(Developer developer) {
        this.developer = developer;
        this.validateSelf();
    }

    public Developer getDeveloper() {
        return developer;
    }
}
