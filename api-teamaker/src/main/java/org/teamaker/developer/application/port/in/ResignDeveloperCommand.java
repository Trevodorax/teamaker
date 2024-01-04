package org.teamaker.developer.application.port.in;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Objects;

public record ResignDeveloperCommand(@Email String email, LocalDate resignationDate) {
        public ResignDeveloperCommand {
            Objects.requireNonNull(email);
            Objects.requireNonNull(resignationDate);
        }
}