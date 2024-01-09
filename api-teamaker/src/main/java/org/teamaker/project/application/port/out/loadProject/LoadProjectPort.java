package org.teamaker.project.application.port.out.loadProject;

import org.teamaker.project.domain.Project;

public interface LoadProjectPort {
    Project loadProject(LoadProjectCommand command);
}
