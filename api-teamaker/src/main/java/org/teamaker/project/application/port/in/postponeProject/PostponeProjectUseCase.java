package org.teamaker.project.application.port.in.postponeProject;

import org.teamaker.project.domain.dto.PostponeProjectDtoResponse;

public interface PostponeProjectUseCase {
    /**
     * Postpone a project
     * @param command the command to postpone a project
     * @return the project with the new dates
     * @throws IllegalStateException if the project is not in a state that can be postponed
     */
    PostponeProjectResponse.Response postponeProject(PostponeProjectCommand command) throws IllegalStateException;
}
