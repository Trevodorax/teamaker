package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoCommand;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoResponse;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoUseCase;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.NoSuchElementException;

@Component
class UpdateDeveloperInfoService implements UpdateDeveloperInfoUseCase {
    private final LoadDeveloperPort loadDeveloperPort;

    private final SaveDeveloperPort saveDeveloperPort;

    public UpdateDeveloperInfoService(LoadDeveloperPort loadDeveloperPort, SaveDeveloperPort saveDeveloperPort) {
        this.loadDeveloperPort = loadDeveloperPort;
        this.saveDeveloperPort = saveDeveloperPort;
    }

    @Override
    public UpdateDeveloperInfoResponse.Response updateDeveloperInfo(UpdateDeveloperInfoCommand command) {
        try {
            Developer developer = loadDeveloperPort.loadDeveloper(new LoadDeveloperCommand(command.getDeveloperId()));
            developer.updateInfo(command.getFullName(), command.getEmail());
            DeveloperResponse result = saveDeveloperPort.saveDeveloper(new SaveDeveloperCommand(developer)).toResponse();
            return new UpdateDeveloperInfoResponse.SuccessResponse(result);
        } catch (NoSuchElementException exception) {
            return new UpdateDeveloperInfoResponse.ErrorResponse("developer not found");
        }
    }
}
