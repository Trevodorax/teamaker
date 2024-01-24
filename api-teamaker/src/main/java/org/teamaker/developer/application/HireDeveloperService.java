package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperResponse;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperUseCase;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;

class HireDeveloperService implements HireDeveloperUseCase {
    private final CreateDeveloperPort createDeveloperPort;

    public HireDeveloperService(CreateDeveloperPort createDeveloperPort) {
        this.createDeveloperPort = createDeveloperPort;
    }

    public HireDeveloperResponse.Response hireDeveloper(HireDeveloperCommand command) {
        try {
            Developer createdDeveloper = createDeveloperPort.createDeveloper(
                    new CreateDeveloperCommand(command.getFullName(), command.getEmail(), LocalDate.now())
            );
            return new HireDeveloperResponse.SuccessResponse(createdDeveloper.toResponse());
        } catch (IllegalArgumentException exception) {
            return new HireDeveloperResponse.ErrorResponse(exception.getMessage());
        }
    }
}
