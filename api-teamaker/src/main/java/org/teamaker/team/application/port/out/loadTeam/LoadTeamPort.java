package org.teamaker.team.application.port.out.loadTeam;

import org.teamaker.team.domain.Team;

public interface LoadTeamPort {
    public Team loadTeam(LoadTeamCommand command);
}
