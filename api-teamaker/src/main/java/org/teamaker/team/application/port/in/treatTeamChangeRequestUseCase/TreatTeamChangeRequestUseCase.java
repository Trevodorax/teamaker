package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

import org.teamaker.team.domain.TeamChangeRequest;

public interface TreatTeamChangeRequestUseCase {
    public TeamChangeRequest treatTeamChangeRequest(TreatTeamChangeRequestCommand command);
}
