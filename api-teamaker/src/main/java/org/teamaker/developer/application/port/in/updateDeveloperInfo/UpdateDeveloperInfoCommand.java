package org.teamaker.developer.application.port.in.updateDeveloperInfo;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UpdateDeveloperInfoCommand extends SelfValidating<UpdateDeveloperInfoCommand> {
    @NotNull
    private String developerId;
    private final String fullName;
    @Email
    private final String email;

    public UpdateDeveloperInfoCommand(String developerId, String fullName, String email) {
        this.developerId = developerId;
        this.fullName = fullName;
        this.email = email;
        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
