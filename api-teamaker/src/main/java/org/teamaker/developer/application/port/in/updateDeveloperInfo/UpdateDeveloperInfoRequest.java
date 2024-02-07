package org.teamaker.developer.application.port.in.updateDeveloperInfo;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;

@Getter
public class UpdateDeveloperInfoRequest extends SelfValidating<UpdateDeveloperInfoRequest> {
    private final String fullName;
    @Email
    private final String email;

    public UpdateDeveloperInfoRequest(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
        this.validateSelf();
    }
}
