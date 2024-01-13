package org.teamaker.developer.application.port.in.resignDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ResignDeveloperCommand extends SelfValidating<ResignDeveloperCommand> {
    @Email
    @NotNull
    private final String email;
    @NotNull
    private final LocalDate resignationDate;

    public ResignDeveloperCommand(String email, LocalDate resignationDate) {
        this.email = email;
        this.resignationDate = resignationDate;

        this.validateSelf();
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }
}
