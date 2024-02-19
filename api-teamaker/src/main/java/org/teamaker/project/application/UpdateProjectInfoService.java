package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoCommand;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoResponse;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

@Component
public class UpdateProjectInfoService implements UpdateProjectInfoUseCase {
    private final LoadProjectPort loadProjectPort;
    private final SaveProjectPort saveProjectPort;

    public UpdateProjectInfoService(LoadProjectPort loadProjectPort, SaveProjectPort saveProjectPort) {
        this.loadProjectPort = loadProjectPort;
        this.saveProjectPort = saveProjectPort;
    }

    @Override
    public UpdateProjectInfoResponse.Response updateProjectInfo(UpdateProjectInfoCommand command) {
        try {
            Project project = loadProjectPort.loadProject(new LoadProjectCommand(command.getProjectId()));
            project.updateInfo(command.getName(), command.getDescription(), command.getPriority());

            Project modifiedProject = saveProjectPort.saveProject(new SaveProjectCommand(project));
            return new UpdateProjectInfoResponse.SuccessResponse(modifiedProject.toResponse());
        } catch (NoSuchElementException e) {
            return new UpdateProjectInfoResponse.ErrorResponse(e.getMessage());
        }
    }
}
