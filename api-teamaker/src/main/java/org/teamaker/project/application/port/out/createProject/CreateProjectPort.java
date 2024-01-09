package org.teamaker.project.application.port.out.createProject;

import org.teamaker.project.domain.Project;

public interface CreateProjectPort {
    public Project createProject(CreateProjectCommand command);
}
