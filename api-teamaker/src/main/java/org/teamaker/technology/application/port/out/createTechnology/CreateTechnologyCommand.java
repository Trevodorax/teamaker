package org.teamaker.technology.application.port.out.createTechnology;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class CreateTechnologyCommand extends SelfValidating<CreateTechnologyPort> {
    @NotNull
    private final String name;

    public CreateTechnologyCommand(String name) {
        this.name = name;

        this.validateSelf();
    }

    public String getName() {
        return name;
    }
}
