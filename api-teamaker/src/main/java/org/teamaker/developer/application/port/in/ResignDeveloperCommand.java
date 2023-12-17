package org.teamaker.developer.application.port.in;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ResignDeveloperCommand extends SelfValidating<ResignDeveloperCommand> {
    @NotNull
    private final String email;
    @NotNull
    private final Date resignationDate;

    public ResignDeveloperCommand(String email, Date resignationDate) {
        this.email = email;
        this.resignationDate = resignationDate;

        this.validateSelf();
    }

    public String getEmail() {
        return email;
    }

    public Date getResignationDate() {
        return resignationDate;
    }
}
