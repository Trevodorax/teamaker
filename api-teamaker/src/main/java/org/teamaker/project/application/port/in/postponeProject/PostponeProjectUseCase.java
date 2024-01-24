package org.teamaker.project.application.port.in.postponeProject;

import org.teamaker.project.domain.dto.PostponeProjectDtoResponse;

public interface PostponeProjectUseCase {
    PostponeProjectResponse.Response postponeProject(PostponeProjectCommand command) throws IllegalStateException;
}
