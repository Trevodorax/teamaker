package org.teamaker.technology.application.port.out.loadTechnology;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadTechnologyCommand extends SelfValidating<LoadTechnologyCommand> {
    @NotNull
    private final String technologyId;

    public LoadTechnologyCommand(String technologyId) {
        this.technologyId = technologyId;
        this.validateSelf();
    }

    public String getTechnologyId() {
        return technologyId;
    }
}
