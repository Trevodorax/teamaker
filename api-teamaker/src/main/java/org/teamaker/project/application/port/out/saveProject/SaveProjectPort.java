package org.teamaker.project.application.port.out.saveProject;

import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

public interface SaveProjectPort {
    /**
     * Save project
     * @param command Project to save
     * @return Saved project
     * @throws NoSuchElementException If project not found
     */
    Project saveProject(SaveProjectCommand command) throws NoSuchElementException;
}
