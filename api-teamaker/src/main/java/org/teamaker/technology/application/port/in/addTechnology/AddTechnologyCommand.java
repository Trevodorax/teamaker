package org.teamaker.technology.application.port.in.addTechnology;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class AddTechnologyCommand extends SelfValidating<AddTechnologyCommand> {
    @NotNull
    private final String name;

    @JsonCreator
    public AddTechnologyCommand(@JsonProperty String name) {
        this.name = name;

        this.validateSelf();
    }

    public String getName() {
        return name;
    }
}
