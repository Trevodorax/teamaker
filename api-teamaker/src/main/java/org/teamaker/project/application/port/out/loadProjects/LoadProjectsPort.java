package org.teamaker.project.application.port.out.loadProjects;

import org.teamaker.project.domain.Project;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoadProjectsPort {
    /**
     * Load all projects
     * @return List of projects or empty list if no projects found
     */
    List<Project> loadProjects();
}
