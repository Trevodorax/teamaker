package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.getProjects.GetProjectsResponse;
import org.teamaker.project.application.port.in.getProjects.GetProjectsUseCase;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.domain.Project;

@Component
public class GetProjectsService implements GetProjectsUseCase {
    private final LoadProjectsPort loadProjectsPort;

    public GetProjectsService(LoadProjectsPort loadProjectsPort) {
        this.loadProjectsPort = loadProjectsPort;
    }

    @Override
    public GetProjectsResponse.Response getProjects() {
        return new GetProjectsResponse.SuccessResponse(loadProjectsPort.loadProjects()
                .stream()
                .map(Project::toResponse)
                .toList());
    }
}
