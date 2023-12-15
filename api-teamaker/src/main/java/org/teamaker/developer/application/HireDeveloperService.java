package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.HireDeveloperUseCase;
import org.teamaker.developer.application.port.out.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

@Component
class HireDeveloperService implements HireDeveloperUseCase {
    private final CreateDeveloperPort createDeveloperPort;

    public HireDeveloperService(CreateDeveloperPort createDeveloperPort) {
        this.createDeveloperPort = createDeveloperPort;
    }

    public Developer hireDeveloper(HireDeveloperCommand command) {
        Developer createdDeveloper = createDeveloperPort.createDeveloper(new CreateDeveloperCommand(command.getFullName(), command.getEmail()));
        // TODO: check if email is already taken
        return createdDeveloper;
    }
}
