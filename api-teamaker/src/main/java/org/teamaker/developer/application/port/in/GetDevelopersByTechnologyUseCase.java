package org.teamaker.developer.application.port.in;

import org.teamaker.developer.application.dto.GetDevelopersByTechnologyResponse;

public interface GetDevelopersByTechnologyUseCase {
    public GetDevelopersByTechnologyResponse getDevelopersByTechnology(GetDevelopersByTechnologyCommand command);
}
