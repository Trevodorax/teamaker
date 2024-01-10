package org.teamaker.project.application.port.in.getProject;

import org.teamaker.project.application.port.dto.ProjectResponse;
import org.teamaker.project.domain.Project;

public interface GetProjectUseCase {
    ProjectResponse getProject(GetProjectCommand command);
}
