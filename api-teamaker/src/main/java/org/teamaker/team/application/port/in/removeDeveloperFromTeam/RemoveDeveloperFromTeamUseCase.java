package org.teamaker.team.application.port.in.removeDeveloperFromTeam;

import org.teamaker.developer.domain.Developer;

public interface RemoveDeveloperFromTeamUseCase {
    Developer removeDeveloperFromTeam(RemoveDeveloperFromTeamCommand command);
}
