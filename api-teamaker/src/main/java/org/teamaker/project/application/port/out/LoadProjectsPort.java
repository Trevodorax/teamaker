package org.teamaker.project.application.port.out;

import java.util.List;

import org.teamaker.project.domain.Project;

public interface LoadProjectsPort {
    public List<Project> loadProjects();
}
