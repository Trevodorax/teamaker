package org.teamaker.developer.application.port.in;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetDevelopersByTechnologyCommand extends SelfValidating<GetDevelopersByTechnologyCommand> {

    @NotNull
    private final String technologyId;

    public GetDevelopersByTechnologyCommand(String technologyId) {
        this.technologyId = technologyId;

        this.validateSelf();
    }

    public String getTechnologyId() {
        return technologyId;
    }
}
