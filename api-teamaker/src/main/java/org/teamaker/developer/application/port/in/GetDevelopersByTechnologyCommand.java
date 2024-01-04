package org.teamaker.developer.application.port.in;

import org.teamaker.shared.validation.SelfValidating;
import javax.validation.constraints.NotNull;

public class GetDevelopersByTechnologyCommand extends SelfValidating<GetDevelopersByTechnologyCommand> {

    @NotNull
    private final String technologyGuid;

    public GetDevelopersByTechnologyCommand(String technologyGuid) {
        this.technologyGuid = technologyGuid;

        this.validateSelf();
    }

    public String getTechnology() {
        return technologyGuid;
    }
}
