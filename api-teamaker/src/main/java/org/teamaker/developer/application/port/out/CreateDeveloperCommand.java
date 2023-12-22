package org.teamaker.developer.application.port.out;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import org.teamaker.shared.validation.SelfValidating;

public class CreateDeveloperCommand extends SelfValidating<CreateDeveloperCommand> {
    @NotNull
    private final String fullName;
    @Email
    private final String email;
    @FutureOrPresent
    private final LocalDate hiringDate;

    public CreateDeveloperCommand(String fullName, String email, LocalDate hiringDate) {
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

    public LocalDate getHiringDate() {
        return hiringDate;
    }
}
