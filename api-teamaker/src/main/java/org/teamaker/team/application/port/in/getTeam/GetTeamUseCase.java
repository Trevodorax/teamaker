package org.teamaker.team.application.port.in.getTeam;

public interface GetTeamUseCase {
    GetTeamResponse.Response getTeam(GetTeamCommand command);
}
