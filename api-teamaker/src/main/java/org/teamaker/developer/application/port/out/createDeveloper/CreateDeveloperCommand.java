package org.teamaker.developer.application.port.out.createDeveloper;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateDeveloperCommand extends SelfValidating<CreateDeveloperCommand> {
    @NotNull
    private final String fullName;
    @Email
    @NotNull
    private final String email;
    @FutureOrPresent
    private final LocalDate hiringDate;

    public CreateDeveloperCommand(String fullName, String email, LocalDate hiringDate) {
        this.fullName = fullName;
        this.email = email;
        if (hiringDate == null) {
            this.hiringDate = LocalDate.now();
        } else {
            this.hiringDate = hiringDate;
        }

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
