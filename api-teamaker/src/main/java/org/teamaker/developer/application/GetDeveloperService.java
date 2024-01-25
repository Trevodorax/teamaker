package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperCommand;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperResponse;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperUseCase;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.util.NoSuchElementException;

class GetDeveloperService implements GetDeveloperUseCase {
    private final LoadDeveloperPort loadDeveloperPort;

    public GetDeveloperService(LoadDeveloperPort loadDeveloperPort) {
        this.loadDeveloperPort = loadDeveloperPort;
    }

    public GetDeveloperResponse.Response getDeveloper(GetDeveloperCommand command){
        try {
            Developer result = loadDeveloperPort.loadDeveloper(new LoadDeveloperCommand(command.getDeveloperId()));
            return new GetDeveloperResponse.SuccessResponse(result.toResponse());
        } catch (NoSuchElementException exception) {
            return new GetDeveloperResponse.ErrorResponse("developer not found");
        }
    }
}
