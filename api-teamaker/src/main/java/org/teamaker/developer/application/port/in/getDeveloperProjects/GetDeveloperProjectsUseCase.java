package org.teamaker.developer.application.port.in.getDeveloperProjects;

import org.teamaker.project.domain.dto.ProjectResponse;

import java.util.List;

public interface GetDeveloperProjectsUseCase {
    List<ProjectResponse> getDeveloperProjects(GetDeveloperProjectsCommand command);
}
