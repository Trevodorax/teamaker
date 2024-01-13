package org.teamaker.project.application.port.in.getProject;

import org.teamaker.project.application.port.dto.ProjectResponse;

public interface GetProjectUseCase {
    ProjectResponse getProject(GetProjectCommand command);
}
