package org.teamaker.project.application.port.in;

import org.teamaker.project.domain.Project;

public interface GetProjectUseCase {
    Project getProject(GetProjectCommand command);
}
