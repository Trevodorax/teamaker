package org.teamaker.technology.application.port.in.addTechnology;

public interface AddTechnologyUseCase {
    AddTechnologyResponse.Response addTechnology(AddTechnologyCommand command);
}
