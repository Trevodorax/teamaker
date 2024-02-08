package org.teamaker.developer.application.port.out.saveDeveloper;

import lombok.Getter;
import org.teamaker.developer.domain.Developer;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class SaveDeveloperCommand extends SelfValidating<SaveDeveloperCommand> {
    @NotNull
    private final Developer developer;

    public SaveDeveloperCommand(Developer developer) {
        this.developer = developer;
        this.validateSelf();
    }
}
