package org.teamaker.team.application.port.in.assignDeveloperToTeam;

public interface AssignDeveloperToTeamUseCase {
    AssignDeveloperToTeamResponse.Response assignDeveloperToTeam(AssignDeveloperToTeamCommand command);
}
