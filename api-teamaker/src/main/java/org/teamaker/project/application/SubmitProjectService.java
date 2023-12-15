package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.SubmitProjectCommand;
import org.teamaker.project.application.port.in.SubmitProjectUseCase;
import org.teamaker.project.application.port.out.CreateProjectCommand;
import org.teamaker.project.application.port.out.CreateProjectPort;
import org.teamaker.project.domain.Project;

@Component
class SubmitProjectService implements SubmitProjectUseCase {
    private final CreateProjectPort createProjectPort;

    public SubmitProjectService(CreateProjectPort createProjectPort) {
        this.createProjectPort = createProjectPort;
    }

    @Override
    public Project submitProject(SubmitProjectCommand command) {
        Project createdProject = createProjectPort.createProject(new CreateProjectCommand(command.getName(), command.getDescription(), command.getPriority(), command.getStartDate(), command.getEndDate()));
        // TODO: create the team and assign the devs to the project
        return createdProject;
    }
}
