package org.teamaker.developer.application.port.in.getDeveloperProjects;

public interface GetDeveloperProjectsUseCase {
    GetDeveloperProjectsResponse.Response getDeveloperProjects(GetDeveloperProjectsCommand command);
}
