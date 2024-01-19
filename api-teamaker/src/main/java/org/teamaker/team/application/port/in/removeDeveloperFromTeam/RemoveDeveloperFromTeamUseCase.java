package org.teamaker.team.application.port.in.removeDeveloperFromTeam;

public interface RemoveDeveloperFromTeamUseCase {
    RemoveDeveloperFromTeamResponse.Response removeDeveloperFromTeam(RemoveDeveloperFromTeamCommand command);
}
