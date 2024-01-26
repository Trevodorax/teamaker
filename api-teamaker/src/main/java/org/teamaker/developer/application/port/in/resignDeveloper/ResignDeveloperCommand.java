package org.teamaker.developer.application.port.in.resignDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ResignDeveloperCommand extends SelfValidating<ResignDeveloperCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final LocalDate resignationDate;

    public ResignDeveloperCommand(String developerId, LocalDate resignationDate) {
        this.developerId = developerId;
        this.resignationDate = resignationDate;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }
}
