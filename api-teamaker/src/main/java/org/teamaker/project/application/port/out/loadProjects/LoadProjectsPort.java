package org.teamaker.project.application.port.out.loadProjects;

import org.teamaker.project.domain.Project;

import java.util.List;

public interface LoadProjectsPort {
    List<Project> loadProjects();
}
