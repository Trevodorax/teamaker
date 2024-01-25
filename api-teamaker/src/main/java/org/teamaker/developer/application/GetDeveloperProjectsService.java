package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsCommand;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsResponse;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsUseCase;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;

import java.util.NoSuchElementException;

class GetDeveloperProjectsService implements GetDeveloperProjectsUseCase {
    private final LoadDeveloperProjectsPort loadDeveloperProjectsPort;

    public GetDeveloperProjectsService(LoadDeveloperProjectsPort loadDeveloperProjectsPort) {
        this.loadDeveloperProjectsPort = loadDeveloperProjectsPort;
    }

    @Override
    public GetDeveloperProjectsResponse.Response getDeveloperProjects(GetDeveloperProjectsCommand command) {
        try {
            return new GetDeveloperProjectsResponse.SuccessResponse(
                    loadDeveloperProjectsPort.loadDeveloperProjects(new LoadDeveloperProjectsCommand(command.getDeveloperId()))
            );
        } catch (NoSuchElementException exception) {
            return new GetDeveloperProjectsResponse.ErrorResponse("developer not found");
        }
    }
}
