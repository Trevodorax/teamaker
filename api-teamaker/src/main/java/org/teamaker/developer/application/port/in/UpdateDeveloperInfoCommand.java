package org.teamaker.developer.application.port.in;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UpdateDeveloperInfoCommand extends SelfValidating<UpdateDeveloperInfoCommand>  {
    private final String fullName;
    @Email
    private final String email;

    public UpdateDeveloperInfoCommand(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;

        this.validateSelf();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
