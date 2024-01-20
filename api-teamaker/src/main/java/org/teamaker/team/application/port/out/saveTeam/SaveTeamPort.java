package org.teamaker.team.application.port.out.saveTeam;

import org.teamaker.team.domain.Team;

public interface SaveTeamPort {
    public Team saveTeam(Team team);
}
