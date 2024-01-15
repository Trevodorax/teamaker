package org.teamaker.team.application.port.in.getTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface GetTeamChangeRequestUseCase {
    public TeamChangeRequest getTeamChangeRequest(GetTeamChangeRequestCommand command);
}
