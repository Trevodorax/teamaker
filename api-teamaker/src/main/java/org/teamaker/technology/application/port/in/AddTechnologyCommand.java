package org.teamaker.technology.application.port.in;

import javax.validation.constraints.NotNull;

import org.teamaker.shared.validation.SelfValidating;

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
