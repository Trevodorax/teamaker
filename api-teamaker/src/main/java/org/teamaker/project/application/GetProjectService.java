package org.teamaker.project.application;

import org.teamaker.project.application.port.in.getProject.GetProjectCommand;
import org.teamaker.project.application.port.in.getProject.GetProjectUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
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