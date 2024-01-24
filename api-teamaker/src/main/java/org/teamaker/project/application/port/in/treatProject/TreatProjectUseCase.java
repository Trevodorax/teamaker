package org.teamaker.project.application.port.in.treatProject;

import org.teamaker.project.domain.dto.TreatProjectDtoResponse;

public interface TreatProjectUseCase {
    /**
     * Change the status of a project
     * @param command the command to change the status of a project
     * @return the project with the new status
     * @throws IllegalStateException if the project is not in a state that can be treated
     */
    TreatProjectResponse.Response treatProject(TreatProjectCommand command) throws IllegalStateException;
}
