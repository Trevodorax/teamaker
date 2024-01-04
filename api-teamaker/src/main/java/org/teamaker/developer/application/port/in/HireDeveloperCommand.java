package org.teamaker.developer.application.port.in;

import javax.validation.constraints.Email;
import java.util.Objects;

public record HireDeveloperCommand(String fullName, @Email String email) {
        public HireDeveloperCommand {
            Objects.requireNonNull(fullName);
            Objects.requireNonNull(email);
        }
}