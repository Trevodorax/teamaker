package org.teamaker.project.application.port.out.loadProjects;

import java.util.List;

import org.teamaker.project.domain.Project;

public interface LoadProjectsPort {
    List<Project> loadProjects();
}
