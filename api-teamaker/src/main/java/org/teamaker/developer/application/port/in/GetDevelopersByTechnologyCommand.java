package org.teamaker.developer.application.port.in;

import org.teamaker.shared.validation.SelfValidating;
import javax.validation.constraints.NotNull;

public class GetDevelopersByTechnologyCommand extends SelfValidating<GetDevelopersByTechnologyCommand> {

    @NotNull
    private final String technology;

    public GetDevelopersByTechnologyCommand(String technology) {
        this.technology = technology;

        this.validateSelf();
    }

    public String getTechnology() {
        return technology;
    }
}
