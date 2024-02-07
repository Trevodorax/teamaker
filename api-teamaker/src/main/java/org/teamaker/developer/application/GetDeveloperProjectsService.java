package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsCommand;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsResponse;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsUseCase;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.project.domain.Project;

import java.util.List;
import java.util.NoSuchElementException;

@Component
class GetDeveloperProjectsService implements GetDeveloperProjectsUseCase {
    private final LoadDeveloperProjectsPort loadDeveloperProjectsPort;

    public GetDeveloperProjectsService(LoadDeveloperProjectsPort loadDeveloperProjectsPort) {
        this.loadDeveloperProjectsPort = loadDeveloperProjectsPort;
    }

    @Override
    public GetDeveloperProjectsResponse.Response getDeveloperProjects(GetDeveloperProjectsCommand command) {
        try {
            List<Project> projects = loadDeveloperProjectsPort.loadDeveloperProjects(new LoadDeveloperProjectsCommand(command.getDeveloperId()));
            return new GetDeveloperProjectsResponse.SuccessResponse(
                    projects.stream()
                            .map(Project::toResponse)
                            .toList()
            );
        } catch (NoSuchElementException exception) {
            return new GetDeveloperProjectsResponse.ErrorResponse("developer not found");
        }
    }
}
