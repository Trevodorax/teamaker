package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import java.util.List;

import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.application.port.in.getProjects.GetProjectsUseCase;
import org.teamaker.project.domain.Project;

@Component
public class GetProjectsService implements GetProjectsUseCase {
    private final LoadProjectsPort loadProjectsPort;

    public GetProjectsService(LoadProjectsPort loadProjectsPort) {
        this.loadProjectsPort = loadProjectsPort;
    }

    @Override
    public List<Project> getProjects() {
        return loadProjectsPort.loadProjects();
    }
}
