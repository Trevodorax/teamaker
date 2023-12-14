package org.teamaker.application;

import org.springframework.stereotype.Component;
import org.teamaker.application.port.in.GetProjectUseCase;
import org.teamaker.application.port.out.LoadProjectPort;
import org.teamaker.domain.Project;
@Component
public class GetProjectService implements GetProjectUseCase {
    private final LoadProjectPort loadProjectPort;

    public GetProjectService(LoadProjectPort loadProjectPort) {
        this.loadProjectPort = loadProjectPort;
    }

    @Override
    public Project getProject(String projectId) {
        return loadProjectPort.loadProject(projectId);
    }
}
