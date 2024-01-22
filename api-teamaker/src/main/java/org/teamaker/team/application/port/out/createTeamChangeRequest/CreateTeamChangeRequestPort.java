package org.teamaker.team.application.port.out.createTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface CreateTeamChangeRequestPort {
    TeamChangeRequest createTeamChangeRequest(CreateTeamChangeRequestCommand command);
}
