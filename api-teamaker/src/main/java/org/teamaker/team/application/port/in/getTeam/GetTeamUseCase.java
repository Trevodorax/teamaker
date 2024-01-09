package org.teamaker.team.application.port.in.getTeam;

import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.domain.Team;

public interface GetTeamUseCase {
    public Team getTeam(GetTeamCommand command);
}
