package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.in.getTeam.GetTeamUseCase;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;

import java.util.List;

@Component
public class GetTeamService implements GetTeamUseCase {
    private final LoadTeamPort loadTeamPort;

    public GetTeamService(LoadTeamPort loadTeamPort) {
        this.loadTeamPort = loadTeamPort;
    }

    @Override
    public List<DeveloperResponse> getTeam(GetTeamCommand command) {
        return loadTeamPort.loadTeam(new LoadTeamCommand(command.getProjectId()))
                .getDevelopers()
                .stream()
                .map(Developer::toResponse)
                .toList();
    }
}
