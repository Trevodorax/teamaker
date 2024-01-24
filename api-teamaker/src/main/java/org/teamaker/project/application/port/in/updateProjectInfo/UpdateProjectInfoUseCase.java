package org.teamaker.project.application.port.in.updateProjectInfo;

public interface UpdateProjectInfoUseCase {
    UpdateProjectInfoResponse.Response updateProjectInfo(UpdateProjectInfoCommand command);
}
