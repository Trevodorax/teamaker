package org.teamaker.project.application.port.in.getProjects;

import java.util.List;

import org.teamaker.project.application.port.dto.ProjectResponse;

public interface GetProjectsUseCase {
    List<ProjectResponse> getProjects();
}
