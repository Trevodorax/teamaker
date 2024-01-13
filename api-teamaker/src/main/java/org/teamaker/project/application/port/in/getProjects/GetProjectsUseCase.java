package org.teamaker.project.application.port.in.getProjects;

import org.teamaker.project.application.port.dto.ProjectResponse;

import java.util.List;

public interface GetProjectsUseCase {
    List<ProjectResponse> getProjects();
}
