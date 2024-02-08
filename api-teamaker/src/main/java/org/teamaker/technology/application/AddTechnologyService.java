package org.teamaker.technology.application;

import org.springframework.stereotype.Component;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyCommand;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyResponse;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyUseCase;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyPort;
import org.teamaker.technology.domain.Technology;

@Component
class AddTechnologyService implements AddTechnologyUseCase {
    private final CreateTechnologyPort createTechnologyPort;

    public AddTechnologyService(CreateTechnologyPort createTechnologyPort) {
        this.createTechnologyPort = createTechnologyPort;
    }

    @Override
    public AddTechnologyResponse.Response addTechnology(AddTechnologyCommand command) {
        try {
            Technology technology = createTechnologyPort.createTechnology(
                    new CreateTechnologyCommand(command.getName())
            );
            return new AddTechnologyResponse.SuccessResponse(technology.toResponse());
        } catch (IllegalArgumentException exception) {
            return new AddTechnologyResponse.ErrorResponse(exception.getMessage());
        }
    }
}
