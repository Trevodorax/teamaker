package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.dto.DeveloperResponse;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperUseCase;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;

@Component
class HireDeveloperService implements HireDeveloperUseCase {
    private final CreateDeveloperPort createDeveloperPort;

    public HireDeveloperService(CreateDeveloperPort createDeveloperPort) {
        this.createDeveloperPort = createDeveloperPort;
    }

    public DeveloperResponse hireDeveloper(HireDeveloperCommand command) {
        Developer createdDeveloper = createDeveloperPort.createDeveloper(new CreateDeveloperCommand(command.getFullName(), command.getEmail(), LocalDate.now()));
        // TODO: check if email is already taken
        return createdDeveloper.toResponse();
    }
}
