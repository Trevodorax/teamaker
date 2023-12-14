package org.teamaker.application.port.out;

import org.teamaker.domain.Project;

public interface LoadProjectPort {
    Project loadProject(String projectId);
}
