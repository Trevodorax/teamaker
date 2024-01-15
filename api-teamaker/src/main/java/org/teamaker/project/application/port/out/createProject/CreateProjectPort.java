package org.teamaker.project.application.port.out.createProject;

import org.teamaker.project.domain.Project;

public interface CreateProjectPort {
    Project createProject(CreateProjectCommand command);
}
