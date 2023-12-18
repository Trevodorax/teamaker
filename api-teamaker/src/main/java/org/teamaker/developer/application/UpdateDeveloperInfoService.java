package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.UpdateDeveloperInfoCommand;
import org.teamaker.developer.application.port.in.UpdateDeveloperInfoUseCase;
import org.teamaker.developer.application.port.out.UpdateDeveloperPersonalInfoCommand;
import org.teamaker.developer.application.port.out.UpdateDeveloperPort;
import org.teamaker.developer.domain.Developer;

public class UpdateDeveloperInfoService implements UpdateDeveloperInfoUseCase {
    private final UpdateDeveloperPort updateDeveloperPort;

    public UpdateDeveloperInfoService(UpdateDeveloperPort updateDeveloperPort) {
        this.updateDeveloperPort = updateDeveloperPort;
    }

    public Developer updateDeveloperInfo(UpdateDeveloperInfoCommand command) {
        Developer updatedDeveloper = updateDeveloperPort.updateDeveloperInfo(new UpdateDeveloperPersonalInfoCommand(command.getFullName(), command.getEmail()));
        return updatedDeveloper;
    }
}
