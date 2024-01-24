package org.teamaker.project.application.port.in.getProject;

public interface GetProjectUseCase {
    GetProjectResponse.Response getProject(GetProjectCommand command);
}
