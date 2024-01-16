package org.teamaker.project.application.port.in.postponeProject;

import org.teamaker.project.domain.dto.PostponeProjectResponse;

public interface PostponeProjectUseCase {
    PostponeProjectResponse postponeProject(PostponeProjectCommand command);
}
