package org.teamaker.developer.application.port.out;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Objects;

public record UpdateDeveloperResignationDateCommand(@Email String email, LocalDate resignationDate) {
    public UpdateDeveloperResignationDateCommand {
        Objects.requireNonNull(email);
        Objects.requireNonNull(resignationDate);
    }
}