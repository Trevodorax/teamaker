package org.teamaker.developer.application.dto;

import java.util.List;
import java.util.Objects;

import org.teamaker.developer.domain.Developer;

public record GetDevelopersByTechnologyResponse(String technology, List<Developer> developers) {

    public GetDevelopersByTechnologyResponse {
        Objects.requireNonNull(technology);
        Objects.requireNonNull(developers);
    }

}
