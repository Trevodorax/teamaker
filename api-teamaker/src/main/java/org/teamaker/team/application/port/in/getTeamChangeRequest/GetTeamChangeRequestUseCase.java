package org.teamaker.team.application.port.in.getTeamChangeRequest;

public interface GetTeamChangeRequestUseCase {
    GetTeamChangeRequestResponse.Response getTeamChangeRequest(GetTeamChangeRequestCommand command);
}
