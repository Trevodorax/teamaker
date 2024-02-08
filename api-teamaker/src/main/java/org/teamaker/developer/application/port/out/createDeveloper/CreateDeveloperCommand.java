package org.teamaker.developer.application.port.out.createDeveloper;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Getter
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
        this.hiringDate = Objects.requireNonNullElseGet(hiringDate, LocalDate::now);

        this.validateSelf();
    }
}
