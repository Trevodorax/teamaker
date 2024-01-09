package org.teamaker.project.application.port.in.getProject;

import org.teamaker.project.domain.Project;

public interface GetProjectUseCase {
    Project getProject(GetProjectCommand command);
}
