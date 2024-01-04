package org.teamaker.technology.application;

import org.springframework.stereotype.Component;

import org.teamaker.technology.application.port.in.AddTechnologyCommand;
import org.teamaker.technology.application.port.in.AddTechnologyUseCase;
import org.teamaker.technology.application.port.out.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.CreateTechnologyPort;
import org.teamaker.technology.domain.Technology;

@Component
class AddTechnologyService implements AddTechnologyUseCase {
    private final CreateTechnologyPort createTechnologyPort;

    public AddTechnologyService(CreateTechnologyPort createTechnologyPort) {
        this.createTechnologyPort = createTechnologyPort;
    }

    @Override
    public Technology addTechnology(AddTechnologyCommand command) {
        Technology createdTechnology = createTechnologyPort.createTechnology(new CreateTechnologyCommand(command.getName()));

        return createdTechnology;
    }
}
