package org.teamaker.team.application.port.out.loadPossibleDevelopersForProject;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public interface LoadPossibleDevelopersForProjectPort {
    /**
     * Retrieves the list of developers that qualify for a project
     * @param command The team we want the developers to be qualified for
     * @return The list of developers
     * @throws IllegalArgumentException If the project is not found
     */
    List<Developer> loadPossibleDevelopersForProject(LoadPossibleDevelopersForProjectCommand command) throws IllegalArgumentException;
}
