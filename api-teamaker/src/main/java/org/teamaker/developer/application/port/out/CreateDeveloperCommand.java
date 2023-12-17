package org.teamaker.developer.application.port.out;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateDeveloperCommand extends SelfValidating<CreateDeveloperCommand> {
    @NotNull
    private final String fullName;
    @Email
    private final String email;
    @FutureOrPresent
    private final Date hiringDate;

    public CreateDeveloperCommand(String fullName, String email, Date hiringDate) {
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;

        this.validateSelf();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Date getHiringDate() {
        return hiringDate;
    }
}
