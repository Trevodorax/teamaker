package org.teamaker.project.application.port.out.createProject;

import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

public interface CreateProjectPort {
    /**
     * Create a Project with the given command
     * @param command The command to create the project
     * @return The created project
     * @throws NoSuchElementException If the project could not be created
     */
    Project createProject(CreateProjectCommand command) throws NoSuchElementException; // Create a Team with the projectId and an empty list of developers
}
