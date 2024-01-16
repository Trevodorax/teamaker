package org.teamaker.team.application.port.out.loadTeam;

import org.teamaker.developer.domain.Developer;
import org.teamaker.team.domain.Team;

import java.util.List;

public interface LoadTeamPort {
    List<Developer> loadTeam(LoadTeamCommand command);
}
