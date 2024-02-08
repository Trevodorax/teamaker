package org.teamaker.technology.application.port.in.getTechnology;

public interface GetTechnologyUseCase {
    GetTechnologyResponse.Response getTechnology(GetTechnologyCommand command);
}
