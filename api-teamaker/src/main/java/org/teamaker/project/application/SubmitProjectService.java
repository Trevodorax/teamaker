package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectUseCase;
import org.teamaker.project.application.port.out.createProject.CreateProjectCommand;
import org.teamaker.project.application.port.out.createProject.CreateProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.dto.ProjectResponse;

import java.util.NoSuchElementException;

@Component
class SubmitProjectService implements SubmitProjectUseCase {
    private final CreateProjectPort createProjectPort;

    public SubmitProjectService(CreateProjectPort createProjectPort) {
        this.createProjectPort = createProjectPort;
    }

    @Override
    public SubmitProjectResponse.Response submitProject(SubmitProjectCommand command) {
        try {
            Project project = createProjectPort.createProject(new CreateProjectCommand(command.getName(), command.getDescription(), command.getPriority(), command.getStartDate(), command.getEndDate(), command.getTechnologies()));
            return new SubmitProjectResponse.SuccessResponse(new ProjectResponse(project.getProjectId(), project.getName(), project.getDescription(), project.getStatus(), project.getPriority(), project.getStartDate(), project.getEndDate(), project.projectProgress()));
        } catch (NoSuchElementException e) {
            return new SubmitProjectResponse.ErrorResponse(e.getMessage());
        }
    }
}
