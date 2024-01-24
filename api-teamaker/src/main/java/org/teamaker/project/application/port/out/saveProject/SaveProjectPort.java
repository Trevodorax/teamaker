package org.teamaker.project.application.port.out.saveProject;

import org.teamaker.project.domain.Project;

public interface SaveProjectPort {
    /**
     * Save project
     * @param command Project to save
     * @return Saved project
     */
    Project saveProject(SaveProjectCommand command);
}
