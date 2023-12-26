package org.teamaker.developer.application.port.out;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import org.teamaker.shared.validation.SelfValidating;

public class UpdateDeveloperResignationDateCommand extends SelfValidating<UpdateDeveloperResignationDateCommand> {
    @Email
    @NotNull
    private final String email;
    @NotNull
    private final LocalDate resignationDate;

    public UpdateDeveloperResignationDateCommand(String email, LocalDate resignationDate) {
        this.email = email;
        this.resignationDate = resignationDate;

        this.validateSelf();
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getHiringDate() {
        return resignationDate;
    }
}
