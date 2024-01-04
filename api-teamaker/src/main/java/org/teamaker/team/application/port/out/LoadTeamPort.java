package org.teamaker.team.application.port.out;

import org.teamaker.team.domain.Team;

public interface LoadTeamPort {
    public Team loadTeam(LoadTeamCommand command);
}
