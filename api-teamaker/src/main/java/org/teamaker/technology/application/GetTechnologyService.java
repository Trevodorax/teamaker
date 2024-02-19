package org.teamaker.technology.application;

import org.springframework.stereotype.Component;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyCommand;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyResponse;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyUseCase;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyCommand;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyPort;

import java.util.NoSuchElementException;

@Component
public class GetTechnologyService implements GetTechnologyUseCase {
    private final LoadTechnologyPort loadTechnologyPort;

    public GetTechnologyService(LoadTechnologyPort loadTechnologyPort) {
        this.loadTechnologyPort = loadTechnologyPort;
    }

    @Override
    public GetTechnologyResponse.Response getTechnology(GetTechnologyCommand command) {
        try {
            return new GetTechnologyResponse.SuccessResponse(loadTechnologyPort.loadTechnology(new LoadTechnologyCommand(command.getTechnologyId())).toResponse());
        } catch(NoSuchElementException exception) {
            return new GetTechnologyResponse.ErrorResponse(exception.getMessage());
        }
    }
}
