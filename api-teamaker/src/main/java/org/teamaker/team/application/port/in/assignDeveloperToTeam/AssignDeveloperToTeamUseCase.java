package org.teamaker.team.application.port.in.assignDeveloperToTeam;

import org.teamaker.developer.domain.dto.DeveloperResponse;

public interface AssignDeveloperToTeamUseCase {
    DeveloperResponse assignDeveloperToTeam(AssignDeveloperToTeamCommand command);
}
