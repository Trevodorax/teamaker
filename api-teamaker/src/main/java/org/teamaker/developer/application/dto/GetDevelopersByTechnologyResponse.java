package org.teamaker.developer.application.dto;

import java.util.List;
import java.util.Objects;

import org.teamaker.developer.domain.Developer;

public record GetDevelopersByTechnologyResponse(String technologyId, List<Developer> developers) {

    public GetDevelopersByTechnologyResponse {
        Objects.requireNonNull(technologyId);
        Objects.requireNonNull(developers);
    }

}
