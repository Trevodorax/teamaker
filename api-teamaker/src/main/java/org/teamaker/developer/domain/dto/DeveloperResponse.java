package org.teamaker.developer.domain.dto;

import java.time.LocalDate;
import java.util.Objects;

public record DeveloperResponse(String developerId, String name, String email, LocalDate hiringDate, LocalDate resignationDate) {
    public DeveloperResponse {
        Objects.requireNonNull(developerId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(hiringDate);
    }
}
