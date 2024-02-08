package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.domain.Team;

@Component
public class LoadTeamAdapter implements LoadTeamPort {
    @Override
    public Team loadTeam(LoadTeamCommand command) {
        return null;
    }
}
