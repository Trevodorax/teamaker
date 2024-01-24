package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyDtoResponse;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyUseCase;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;

import java.util.List;

@Component
class GetDevelopersByTechnologyService implements GetDevelopersByTechnologyUseCase {

    private final FindDevelopersByTechnologyPort findDevelopersByTechnologyPort;

    public GetDevelopersByTechnologyService(FindDevelopersByTechnologyPort findDevelopersByTechnologyPort) {
        this.findDevelopersByTechnologyPort = findDevelopersByTechnologyPort;
    }

    @Override
    public GetDevelopersByTechnologyResponse.Response getDevelopersByTechnology(GetDevelopersByTechnologyCommand command) {
        List<Developer> developers = findDevelopersByTechnologyPort.findDevelopersByTechnology(new FindDevelopersByTechnologyCommand(command.getTechnologyId()));

        return new GetDevelopersByTechnologyResponse.SuccessResponse(
                new GetDevelopersByTechnologyDtoResponse(
                        command.getTechnologyId(),
                        developers
                                .stream()
                                .map(Developer::toResponse)
                                .toList()
                )
        );
    }

}
