package org.teamaker.developer.application.port.out.loadDeveloperProjects;

import org.teamaker.project.domain.Project;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoadDeveloperProjectsPort {
    List<Project> loadDeveloperProjects(LoadDeveloperProjectsCommand command) throws NoSuchElementException;
}
