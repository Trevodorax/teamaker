package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.domain.dto.PostponeProjectResponse;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectCommand;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;

@Component
public class PostponeProjectService implements PostponeProjectUseCase {
    private final SaveProjectPort saveProjectPort;
    private final LoadProjectPort loadProjectPort;

    public PostponeProjectService(SaveProjectPort saveProjectPort, LoadProjectPort loadProjectPort) {
        this.saveProjectPort = saveProjectPort;
        this.loadProjectPort = loadProjectPort;
    }

    @Override
    public PostponeProjectResponse postponeProject(PostponeProjectCommand command) {
        Project project = loadProjectPort.loadProject(new LoadProjectCommand(command.getProjectId()));
        if (project.getStatus() != ProjectStatus.PENDING) {
            throw new IllegalStateException("Project cannot be postponed if it is not pending");
        }

        if (command.getNewEndDate() == null) {
            project.postpone(command.getNewStartDate());
        } else {
            project.postpone(command.getNewStartDate(), command.getNewEndDate());
        }

        Project modifiedProject = saveProjectPort.saveProject(new SaveProjectCommand(project));

        return new PostponeProjectResponse(modifiedProject.getProjectId(), modifiedProject.getStartDate(), modifiedProject.getEndDate());
    }
}
