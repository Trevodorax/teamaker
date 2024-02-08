package org.teamaker.technology.application.port.in.addTechnology;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class AddTechnologyCommand extends SelfValidating<AddTechnologyCommand> {
    @NotNull
    private final String name;

    public AddTechnologyCommand(String name) {
        this.name = name;

        this.validateSelf();
    }

    public String getName() {
        return name;
    }
}
