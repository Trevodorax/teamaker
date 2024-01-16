package org.teamaker.team.application.port.in.getTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface GetTeamChangeRequestUseCase {
    TeamChangeRequest getTeamChangeRequest(GetTeamChangeRequestCommand command);
}
