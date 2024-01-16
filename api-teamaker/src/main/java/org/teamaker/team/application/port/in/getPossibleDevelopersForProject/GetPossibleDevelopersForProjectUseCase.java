package org.teamaker.team.application.port.in.getPossibleDevelopersForProject;

import java.util.List;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;

public interface GetPossibleDevelopersForProjectUseCase {
    List<DeveloperResponse> getPossibleDevelopersForProjectUseCase(GetPossibleDevelopersForProjectCommand command);
}
