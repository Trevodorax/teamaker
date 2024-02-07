package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.project.domain.Project;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LoadDeveloperProjectsAdapter implements LoadDeveloperProjectsPort {
    @Override
    public List<Project> loadDeveloperProjects(LoadDeveloperProjectsCommand command) throws NoSuchElementException {
//        return null;
        return List.of();
    }
}
