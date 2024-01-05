package org.teamaker.project.application.port.out;

import org.teamaker.project.domain.Project;

public interface LoadProjectPort {
    Project loadProject(LoadProjectCommand command);
}
