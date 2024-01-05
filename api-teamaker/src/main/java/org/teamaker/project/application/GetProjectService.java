package org.teamaker.project.application;

import org.teamaker.project.application.port.in.GetProjectCommand;
import org.teamaker.project.application.port.in.GetProjectUseCase;
import org.teamaker.project.application.port.out.LoadProjectCommand;
import org.teamaker.project.application.port.out.LoadProjectPort;
import org.teamaker.project.domain.Project;

public class GetProjectService implements GetProjectUseCase {
    private final LoadProjectPort loadProjectPort;

    public GetProjectService(LoadProjectPort loadProjectPort) {
        this.loadProjectPort = loadProjectPort;
    }

    @Override
    public Project getProject(GetProjectCommand command) {
        return loadProjectPort.loadProject(new LoadProjectCommand(command.getProjectId()));
    }
}
