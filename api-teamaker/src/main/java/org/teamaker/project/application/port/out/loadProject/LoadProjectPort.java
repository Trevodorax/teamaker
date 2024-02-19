package org.teamaker.project.application.port.out.loadProject;

import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

public interface LoadProjectPort {
    /**
     * Load information about a project.
     * @param command The command to load the project
     * @return The loaded project
     * @throws IllegalArgumentException If the project does not exist
     */
    Project loadProject(LoadProjectCommand command) throws NoSuchElementException;
}
