package org.teamaker.developer.application.port.in;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class HireDeveloperCommand extends SelfValidating<HireDeveloperCommand> {
    @NotNull
    private  final String fullName;
    @Email
    private final String email;

    public HireDeveloperCommand(String fullName, String email) {
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
