package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

import org.teamaker.team.domain.TeamChangeRequest;

public interface TreatTeamChangeRequestUseCase {
    TeamChangeRequest treatTeamChangeRequest(TreatTeamChangeRequestCommand command);
}
