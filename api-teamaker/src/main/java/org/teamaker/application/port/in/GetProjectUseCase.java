package org.teamaker.application.port.in;

import org.teamaker.domain.Project;

public interface GetProjectUseCase {
    Project getProject(String projectId);
}
