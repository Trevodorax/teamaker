package org.teamaker.developer.application.port.in.getDeveloperProjects;

import org.teamaker.project.domain.dto.ProjectResponse;

import java.util.List;

public interface GetDeveloperProjectsUseCase {
    GetDeveloperProjectsResponse.Response getDeveloperProjects(GetDeveloperProjectsCommand command);
}
