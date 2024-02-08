package org.teamaker.project.application.port.in.submitProject;

public interface SubmitProjectUseCase {
    SubmitProjectResponse.Response submitProject(SubmitProjectCommand command);
}
