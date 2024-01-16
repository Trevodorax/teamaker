package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.project.application.port.in.getProjects.GetProjectsUseCase;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.domain.Project;

import java.util.List;

@Component
public class GetProjectsService implements GetProjectsUseCase {
    private final LoadProjectsPort loadProjectsPort;

    public GetProjectsService(LoadProjectsPort loadProjectsPort) {
        this.loadProjectsPort = loadProjectsPort;
    }

    @Override
    public List<ProjectResponse> getProjects() {
        return loadProjectsPort.loadProjects()
                .stream()
                .map(Project::toResponse)
                .toList();
    }
}
