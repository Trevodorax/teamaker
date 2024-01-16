package org.teamaker.technology.domain.dto;

import java.util.Objects;

public record TechnologyResponse(String technologyId, String name) {
    public TechnologyResponse {
        Objects.requireNonNull(technologyId);
        Objects.requireNonNull(name);
    }
}
