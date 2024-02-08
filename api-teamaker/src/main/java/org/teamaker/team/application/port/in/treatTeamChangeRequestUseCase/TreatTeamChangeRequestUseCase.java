package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

public interface TreatTeamChangeRequestUseCase {
    TreatTeamChangeRequestResponse.Response treatTeamChangeRequest(TreatTeamChangeRequestCommand command);
}
