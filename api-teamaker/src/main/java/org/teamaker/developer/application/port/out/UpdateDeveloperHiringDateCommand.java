package org.teamaker.developer.application.port.out;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import org.teamaker.shared.validation.SelfValidating;

public class UpdateDeveloperHiringDateCommand extends SelfValidating<UpdateDeveloperHiringDateCommand> {
    @Email
    @NotNull
    private final String email;
    @NotNull
    private final LocalDate hiringDate;

    public UpdateDeveloperHiringDateCommand(String email, LocalDate hiringDate) {
        this.email = email;
        this.hiringDate = hiringDate;

        this.validateSelf();
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }
}
