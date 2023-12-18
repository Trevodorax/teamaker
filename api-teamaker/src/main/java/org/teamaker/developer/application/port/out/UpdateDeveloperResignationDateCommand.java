package org.teamaker.developer.application.port.out;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateDeveloperResignationDateCommand extends SelfValidating<UpdateDeveloperResignationDateCommand> {
    @Email
    private final String email;
    @NotNull
    private final Date resignationDate;

    public UpdateDeveloperResignationDateCommand(String email, Date resignationDate) {
        this.email = email;
        this.resignationDate = resignationDate;

        this.validateSelf();
    }

    public String getEmail() {
        return email;
    }

    public Date getHiringDate() {
        return resignationDate;
    }
}
