package org.teamaker.team.application.port.out.loadPossibleDevelopersForProject;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public interface LoadPossibleDevelopersForProjectPort {
    List<Developer> loadPossibleDevelopersForProject(LoadPossibleDevelopersForProjectCommand command);
}
