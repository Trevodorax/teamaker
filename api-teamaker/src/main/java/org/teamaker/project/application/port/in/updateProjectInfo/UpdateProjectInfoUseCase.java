package org.teamaker.project.application.port.in.updateProjectInfo;

import org.teamaker.project.application.port.dto.ProjectResponse;

public interface UpdateProjectInfoUseCase {
    ProjectResponse updateProjectInfo(UpdateProjectInfoCommand command);
}
