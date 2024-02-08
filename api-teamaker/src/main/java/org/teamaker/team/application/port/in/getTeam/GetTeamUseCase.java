package org.teamaker.team.application.port.in.getTeam;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public interface GetTeamUseCase {
    List<DeveloperResponse> getTeam(GetTeamCommand command);
}
