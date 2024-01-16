package org.teamaker.developer.application.port.in.getDevelopersByTechnology;

import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyResponse;

public interface GetDevelopersByTechnologyUseCase {
    GetDevelopersByTechnologyResponse getDevelopersByTechnology(GetDevelopersByTechnologyCommand command);
}
