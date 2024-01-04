package org.teamaker.team.application.port.in;

import org.teamaker.team.domain.Team;

public interface GetTeamUseCase {
    public Team getTeam(GetTeamCommand command);
}
