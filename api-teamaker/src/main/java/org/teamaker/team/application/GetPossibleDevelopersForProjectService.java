package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.domain.Developer;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForProjectUseCase;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForTeamResponse;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectPort;

import java.util.List;

@Component
public class GetPossibleDevelopersForProjectService implements GetPossibleDevelopersForProjectUseCase {
    private final LoadPossibleDevelopersForProjectPort loadPossibleDevelopersForProjectPort;

    public GetPossibleDevelopersForProjectService(LoadPossibleDevelopersForProjectPort loadPossibleDevelopersForProjectPort) {
        this.loadPossibleDevelopersForProjectPort = loadPossibleDevelopersForProjectPort;
    }

    @Override
    public GetPossibleDevelopersForTeamResponse.Response getPossibleDevelopersForProjectUseCase(GetPossibleDevelopersForProjectCommand command) {
        try {
            List<Developer> developers = loadPossibleDevelopersForProjectPort.loadPossibleDevelopersForProject(
                    new LoadPossibleDevelopersForProjectCommand(command.getProjectId())
            );

            return new GetPossibleDevelopersForTeamResponse.SuccessResponse(
                    developers.stream().map(Developer::toResponse).toList()
            );

        } catch(IllegalArgumentException exception) {
            return new GetPossibleDevelopersForTeamResponse.ErrorResponse(exception.getMessage());
        }
    }
}
