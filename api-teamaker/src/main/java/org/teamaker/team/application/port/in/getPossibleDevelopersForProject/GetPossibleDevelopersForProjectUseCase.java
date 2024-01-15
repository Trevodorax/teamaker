package org.teamaker.team.application.port.in.getPossibleDevelopersForProject;

import java.util.List;

import org.teamaker.developer.domain.Developer;

public interface GetPossibleDevelopersForProjectUseCase {
    public List<Developer> getPossibleDevelopersForProjectUseCase(GetPossibleDevelopersForProjectCommand command);
}
