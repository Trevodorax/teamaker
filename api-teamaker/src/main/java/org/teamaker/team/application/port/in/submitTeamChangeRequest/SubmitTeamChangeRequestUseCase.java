package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface SubmitTeamChangeRequestUseCase {
    public TeamChangeRequest submitTeamChangeRequest(SubmitTeamChangeRequestCommand command);
}
