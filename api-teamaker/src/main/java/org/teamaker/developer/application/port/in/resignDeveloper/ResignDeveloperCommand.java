package org.teamaker.developer.application.port.in.resignDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ResignDeveloperCommand extends SelfValidating<ResignDeveloperCommand> {
    @NotNull
    private final String developerId;

    public ResignDeveloperCommand(String developerId) {
        this.developerId = developerId;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
