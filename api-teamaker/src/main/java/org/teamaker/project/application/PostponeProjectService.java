package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectCommand;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectResponse;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.project.domain.dto.PostponeProjectDtoResponse;

import java.util.NoSuchElementException;

@Component
public class PostponeProjectService implements PostponeProjectUseCase {
    private final SaveProjectPort saveProjectPort;
    private final LoadProjectPort loadProjectPort;

    public PostponeProjectService(SaveProjectPort saveProjectPort, LoadProjectPort loadProjectPort) {
        this.saveProjectPort = saveProjectPort;
        this.loadProjectPort = loadProjectPort;
    }

    @Override
    public PostponeProjectResponse.Response postponeProject(PostponeProjectCommand command) {
        try {
            Project project = loadProjectPort.loadProject(new LoadProjectCommand(command.getProjectId()));
            if (project.getStatus() != ProjectStatus.PENDING) {
                return new PostponeProjectResponse.ErrorResponse("Project cannot be postponed if it is not pending");
            }

            if (command.getNewEndDate() == null) {
                project.postpone(command.getNewStartDate());
            } else {
                project.postpone(command.getNewStartDate(), command.getNewEndDate());
            }

            Project modifiedProject = saveProjectPort.saveProject(new SaveProjectCommand(project));

            return new PostponeProjectResponse.SuccessResponse(new PostponeProjectDtoResponse(modifiedProject.getProjectId(), modifiedProject.getStartDate(), modifiedProject.getEndDate()));
        } catch (NoSuchElementException e) {
            return new PostponeProjectResponse.ErrorResponse(e.getMessage());
        }
    }
}
