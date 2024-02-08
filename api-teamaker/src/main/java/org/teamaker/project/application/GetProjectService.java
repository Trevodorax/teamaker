package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.getProject.GetProjectCommand;
import org.teamaker.project.application.port.in.getProject.GetProjectResponse;
import org.teamaker.project.application.port.in.getProject.GetProjectUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;

import java.util.NoSuchElementException;

@Component
public class GetProjectService implements GetProjectUseCase {
    private final LoadProjectPort loadProjectPort;

    public GetProjectService(LoadProjectPort loadProjectPort) {
        this.loadProjectPort = loadProjectPort;
    }

    @Override
    public GetProjectResponse.Response getProject(GetProjectCommand command) {
        try {
            return new GetProjectResponse.SuccessResponse(loadProjectPort.loadProject(new LoadProjectCommand(command.getProjectId())).toResponse());
        } catch(NoSuchElementException exception) {
            return new GetProjectResponse.ErrorResponse(exception.getMessage());
        }
    }
}
