package org.teamaker.developer.application.port.in.updateDeveloperInfo;

import lombok.Getter;
import lombok.Setter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateDeveloperInfoCommand extends SelfValidating<UpdateDeveloperInfoCommand> {
    @Setter
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
}
