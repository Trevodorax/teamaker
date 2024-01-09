package org.teamaker.team.application;

import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.in.getTeam.GetTeamUseCase;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
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
