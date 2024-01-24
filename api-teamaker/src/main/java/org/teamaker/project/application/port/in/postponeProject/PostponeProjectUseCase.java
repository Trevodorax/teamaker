package org.teamaker.project.application.port.in.postponeProject;

import org.teamaker.project.domain.dto.PostponeProjectDtoResponse;

public interface PostponeProjectUseCase {
    /**
     * Postpone a project
     * @param command the command to postpone a project
     * @return the project with the new dates or an error message if the project cannot be postponed
     */
    PostponeProjectResponse.Response postponeProject(PostponeProjectCommand command);
}
