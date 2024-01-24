package org.teamaker.project.application.port.in.getProjects;

import org.teamaker.project.domain.dto.ProjectResponse;

import java.util.List;

public interface GetProjectsUseCase {
    GetProjectsResponse.Response getProjects();
}
