package org.teamaker.developer.domain.dto;

import org.teamaker.developer.domain.Developer;

import java.util.List;
import java.util.Objects;

public record GetDevelopersByTechnologyResponse(String technologyId, List<Developer> developers) {

    public GetDevelopersByTechnologyResponse {
        Objects.requireNonNull(technologyId);
        Objects.requireNonNull(developers);
    }

}
