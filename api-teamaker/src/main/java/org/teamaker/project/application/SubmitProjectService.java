package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectUseCase;
import org.teamaker.project.application.port.out.createProject.CreateProjectCommand;
import org.teamaker.project.application.port.out.createProject.CreateProjectPort;
import org.teamaker.project.domain.Project;

@Component
class SubmitProjectService implements SubmitProjectUseCase {
    private final CreateProjectPort createProjectPort;

    public SubmitProjectService(CreateProjectPort createProjectPort) {
        this.createProjectPort = createProjectPort;
    }

    @Override
    public ProjectResponse submitProject(SubmitProjectCommand command) {
        Project createdProject = createProjectPort.createProject(new CreateProjectCommand(command.getName(), command.getDescription(), command.getPriority(), command.getStartDate(), command.getEndDate(), command.getTechnologies()));
        // TODO: create the team and assign the devs to the project
        return createdProject.toResponse();
    }
}
