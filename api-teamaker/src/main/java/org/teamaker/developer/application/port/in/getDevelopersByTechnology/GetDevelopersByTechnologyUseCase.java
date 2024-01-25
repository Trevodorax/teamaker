package org.teamaker.developer.application.port.in.getDevelopersByTechnology;

import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyDtoResponse;

public interface GetDevelopersByTechnologyUseCase {
    GetDevelopersByTechnologyResponse.Response getDevelopersByTechnology(GetDevelopersByTechnologyCommand command);
}
