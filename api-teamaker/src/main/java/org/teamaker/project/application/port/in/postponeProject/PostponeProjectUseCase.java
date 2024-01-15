package org.teamaker.project.application.port.in.postponeProject;

import org.teamaker.project.application.port.dto.PostponeProjectResponse;

public interface PostponeProjectUseCase {
    PostponeProjectResponse postponeProject(PostponeProjectCommand command);
}
