package org.teamaker.team.application;

import org.teamaker.team.application.port.in.GetTeamCommand;
import org.teamaker.team.application.port.in.GetTeamUseCase;
import org.teamaker.team.application.port.out.LoadTeamCommand;
import org.teamaker.team.application.port.out.LoadTeamPort;
import org.teamaker.team.domain.Team;

public class GetTeamService implements GetTeamUseCase {
    private final LoadTeamPort loadTeamPort;

    public GetTeamService(LoadTeamPort loadTeamPort) {
        this.loadTeamPort = loadTeamPort;
    }

    @Override
    public Team getTeam(GetTeamCommand command) {
        return loadTeamPort.loadTeam(new LoadTeamCommand(command.getId()));
    }
}
