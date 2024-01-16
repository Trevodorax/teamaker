package org.teamaker.project.application.port.in.updateProjectInfo;

import org.teamaker.project.domain.dto.ProjectResponse;

public interface UpdateProjectInfoUseCase {
    ProjectResponse updateProjectInfo(UpdateProjectInfoCommand command);
}
