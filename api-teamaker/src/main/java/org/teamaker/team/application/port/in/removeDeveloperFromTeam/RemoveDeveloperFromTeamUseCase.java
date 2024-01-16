package org.teamaker.team.application.port.in.removeDeveloperFromTeam;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;

public interface RemoveDeveloperFromTeamUseCase {
    DeveloperResponse removeDeveloperFromTeam(RemoveDeveloperFromTeamCommand command);
}
