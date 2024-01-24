package org.teamaker.project.application.port.in.getNextProject;

import org.teamaker.project.domain.dto.ProjectResponse;

public interface GetNextProject {
    GetNextProjectResponse.Response getNextProject();
}
