package org.teamaker.developer.application.port.out;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.Objects;

public record CreateDeveloperCommand(String fullName, @Email String email, @FutureOrPresent LocalDate hiringDate) {
            public CreateDeveloperCommand {
                Objects.requireNonNull(fullName);
                Objects.requireNonNull(email);
                Objects.requireNonNull(hiringDate);
            }
}