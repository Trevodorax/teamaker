package org.teamaker.project.application.port.out.loadProject;

import org.teamaker.project.domain.Project;

public interface LoadProjectPort {
    /**
     * Load information about a project.
     * @param command The command to load the project
     * @return The loaded project
     * @throws IllegalArgumentException If the project does not exist
     */
    Project loadProject(LoadProjectCommand command) throws IllegalArgumentException;
}
