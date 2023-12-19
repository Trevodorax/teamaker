package org.teamaker.developer.application.port.out;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateDeveloperHiringDateCommand extends SelfValidating<UpdateDeveloperHiringDateCommand> {
    @Email
    private final String email;
    @NotNull
    private final Date hiringDate;

    public UpdateDeveloperHiringDateCommand(String email, Date hiringDate) {
        this.email = email;
        this.hiringDate = hiringDate;

        this.validateSelf();
    }

    public String getEmail() {
        return email;
    }

    public Date getHiringDate() {
        return hiringDate;
    }
}
