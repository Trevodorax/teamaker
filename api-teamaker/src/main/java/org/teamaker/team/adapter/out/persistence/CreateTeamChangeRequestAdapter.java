package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

@Component
public class CreateTeamChangeRequestAdapter implements CreateTeamChangeRequestPort {
    @Override
    public TeamChangeRequest createTeamChangeRequest(CreateTeamChangeRequestCommand command) {
        return null;
    }
}
