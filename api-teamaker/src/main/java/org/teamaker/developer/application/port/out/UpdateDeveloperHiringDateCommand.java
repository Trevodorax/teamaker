package org.teamaker.developer.application.port.out;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Objects;

public record UpdateDeveloperHiringDateCommand(@Email String email, LocalDate hiringDate) {
    public UpdateDeveloperHiringDateCommand {
        Objects.requireNonNull(email);
        Objects.requireNonNull(hiringDate);
    }
}