package org.teamaker.team.application.port.out.loadTeam;

import org.teamaker.team.domain.Team;

import java.util.List;

public interface LoadTeamPort {
    Team loadTeam(LoadTeamCommand command);
}
