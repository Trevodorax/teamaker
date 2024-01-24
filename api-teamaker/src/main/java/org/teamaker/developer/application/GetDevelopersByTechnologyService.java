package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyUseCase;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;

@Component
class GetDevelopersByTechnologyService implements GetDevelopersByTechnologyUseCase {

    private final FindDevelopersByTechnologyPort findDevelopersByTechnologyPort;

    public GetDevelopersByTechnologyService(FindDevelopersByTechnologyPort findDevelopersByTechnologyPort) {
        this.findDevelopersByTechnologyPort = findDevelopersByTechnologyPort;
    }

    @Override
    public GetDevelopersByTechnologyResponse getDevelopersByTechnology(GetDevelopersByTechnologyCommand command) {
        return new GetDevelopersByTechnologyResponse(command.getTechnologyId(), findDevelopersByTechnologyPort.findDevelopersByTechnology(new FindDevelopersByTechnologyCommand(command.getTechnologyId())));
    }

}
