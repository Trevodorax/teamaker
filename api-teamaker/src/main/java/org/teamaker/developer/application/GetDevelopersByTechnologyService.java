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
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyCommand;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyPort;
import org.teamaker.technology.domain.Technology;

import java.util.List;
import java.util.NoSuchElementException;

@Component
class GetDevelopersByTechnologyService implements GetDevelopersByTechnologyUseCase {

    private final FindDevelopersByTechnologyPort findDevelopersByTechnologyPort;
    private final LoadTechnologyPort loadTechnologyPort;

    public GetDevelopersByTechnologyService(FindDevelopersByTechnologyPort findDevelopersByTechnologyPort, LoadTechnologyPort loadTechnologyPort) {
        this.findDevelopersByTechnologyPort = findDevelopersByTechnologyPort;
        this.loadTechnologyPort = loadTechnologyPort;
    }

    @Override
    public GetDevelopersByTechnologyResponse.Response getDevelopersByTechnology(GetDevelopersByTechnologyCommand command) {
        try {
            Technology technology = loadTechnologyPort.loadTechnology(new LoadTechnologyCommand(command.getTechnologyId()));
            List<Developer> developers = findDevelopersByTechnologyPort.findDevelopersByTechnology(new FindDevelopersByTechnologyCommand(technology));

            return new GetDevelopersByTechnologyResponse.SuccessResponse(
                    new GetDevelopersByTechnologyDtoResponse(
                            command.getTechnologyId(),
                            developers
                                    .stream()
                                    .map(Developer::toResponse)
                                    .toList()
                    )
            );
        } catch (NoSuchElementException exception) {
            return new GetDevelopersByTechnologyResponse.ErrorResponse("Technology not found");
        }
    }

}
