package org.teamaker.project.application.port.in.submitProject;

import org.teamaker.project.application.port.dto.ProjectResponse;
import org.teamaker.project.domain.Project;

public interface SubmitProjectUseCase {
    ProjectResponse submitProject(SubmitProjectCommand command);
}
