package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

@Component
public class SaveTeamAdapter implements SaveTeamPort {
    @Override
    public Team saveTeam(SaveTeamCommand command) {
        return null;
    }
}
