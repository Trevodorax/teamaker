package org.teamaker.developer.application;

import org.teamaker.developer.application.dto.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.in.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.GetDevelopersByTechnologyUseCase;
import org.teamaker.developer.application.port.out.FindDevelopersByTechnologyPort;

public class GetDevelopersByTechnologyService implements GetDevelopersByTechnologyUseCase {

    private final FindDevelopersByTechnologyPort findDevelopersByTechnologyPort;

    public GetDevelopersByTechnologyService(FindDevelopersByTechnologyPort findDevelopersByTechnologyPort) {
        this.findDevelopersByTechnologyPort = findDevelopersByTechnologyPort;
    }

    @Override
    public GetDevelopersByTechnologyResponse getDevelopersByTechnology(GetDevelopersByTechnologyCommand command) {
        return new GetDevelopersByTechnologyResponse(command.getTechnology(), findDevelopersByTechnologyPort.findDevelopersByTechnology(command.getTechnology()));
    }

}
