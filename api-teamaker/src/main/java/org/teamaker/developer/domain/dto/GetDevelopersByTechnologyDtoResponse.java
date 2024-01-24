package org.teamaker.developer.domain.dto;

import java.util.List;
import java.util.Objects;

public record GetDevelopersByTechnologyDtoResponse(String technologyId, List<DeveloperResponse> developers) {

    public GetDevelopersByTechnologyDtoResponse {
        Objects.requireNonNull(technologyId);
        Objects.requireNonNull(developers);
    }

}
