package org.teamaker.project.application.port.in;

import java.util.List;

import org.teamaker.project.domain.Project;

public interface GetProjectsUseCase {
    List<Project> getProjects();
}
