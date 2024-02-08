package org.teamaker.developer.application.port.in.getDevelopersByTechnology;

public interface GetDevelopersByTechnologyUseCase {
    GetDevelopersByTechnologyResponse.Response getDevelopersByTechnology(GetDevelopersByTechnologyCommand command);
}
