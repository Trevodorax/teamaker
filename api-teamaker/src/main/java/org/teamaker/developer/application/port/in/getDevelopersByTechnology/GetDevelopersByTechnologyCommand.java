package org.teamaker.developer.application.port.in.getDevelopersByTechnology;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class GetDevelopersByTechnologyCommand extends SelfValidating<GetDevelopersByTechnologyCommand> {
    @NotNull
    private final String technologyId;

    public GetDevelopersByTechnologyCommand(String technologyId) {
        this.technologyId = technologyId;

        this.validateSelf();
    }
}
