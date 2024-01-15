package org.teamaker.team.application.port.in.assignDeveloperToTeam;

import org.teamaker.developer.domain.Developer;

public interface AssignDeveloperToTeamUseCase {
    Developer assignDeveloperToTeam(AssignDeveloperToTeamCommand command);
}
