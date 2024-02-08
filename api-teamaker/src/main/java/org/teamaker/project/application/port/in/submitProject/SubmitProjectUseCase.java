package org.teamaker.project.application.port.in.submitProject;

import org.teamaker.project.domain.dto.ProjectResponse;

public interface SubmitProjectUseCase {
    SubmitProjectResponse.Response submitProject(SubmitProjectCommand command);
}
