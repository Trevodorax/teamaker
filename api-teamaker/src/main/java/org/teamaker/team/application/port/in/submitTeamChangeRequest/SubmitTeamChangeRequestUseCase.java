package org.teamaker.team.application.port.in.submitTeamChangeRequest;

public interface SubmitTeamChangeRequestUseCase {
    SubmitTeamChangeRequestResponse.Response submitTeamChangeRequest(SubmitTeamChangeRequestCommand command);
}
