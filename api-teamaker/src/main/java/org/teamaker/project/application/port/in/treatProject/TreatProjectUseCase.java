package org.teamaker.project.application.port.in.treatProject;

public interface TreatProjectUseCase {
    /**
     * Change the status of a project
     * @param command the command to change the status of a project
     * @return the project with the new status or an error message if the project cannot be treated
     */
    TreatProjectResponse.Response treatProject(TreatProjectCommand command);
}
