package org.teamaker.developer.application.port.out.loadDeveloperProjects;

import org.teamaker.project.domain.Project;

import java.util.List;

public interface LoadDeveloperProjectsPort {
    List<Project> loadDeveloperProjects(LoadDeveloperProjectsCommand command);
}
