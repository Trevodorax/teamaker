package org.teamaker.developer.domain.dto;

import org.teamaker.developer.domain.Developer;

import java.util.List;
import java.util.Objects;

public record GetDevelopersByTechnologyResponse(String technologyId, List<DeveloperResponse> developers) {

    public GetDevelopersByTechnologyResponse {
        Objects.requireNonNull(technologyId);
        Objects.requireNonNull(developers);
    }

}
