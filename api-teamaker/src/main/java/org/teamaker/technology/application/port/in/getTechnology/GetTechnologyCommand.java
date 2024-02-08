package org.teamaker.technology.application.port.in.getTechnology;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class GetTechnologyCommand extends SelfValidating<GetTechnologyCommand> {
    @NotNull
    private final String technologyId;

    public GetTechnologyCommand(String technologyId) {
        this.technologyId = technologyId;

        this.validateSelf();
    }
}
