package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

import java.util.List;

@Component
public class LoadTeamChangeRequestAdapter implements LoadTeamChangeRequestPort {
    @Override
    public TeamChangeRequest loadTeamChangeRequest(LoadTeamChangeRequestCommand command) throws IllegalArgumentException {
        return null;
    }
}
