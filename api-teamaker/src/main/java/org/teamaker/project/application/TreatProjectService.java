package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.treatProject.TreatProjectCommand;
import org.teamaker.project.application.port.in.treatProject.TreatProjectResponse;
import org.teamaker.project.application.port.in.treatProject.TreatProjectUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.project.domain.dto.TreatProjectDtoResponse;

import java.util.NoSuchElementException;

@Component
public class TreatProjectService implements TreatProjectUseCase {
    private final LoadProjectPort loadProjectPort;
    private final SaveProjectPort saveProjectPort;

    public TreatProjectService(LoadProjectPort loadProjectPort, SaveProjectPort saveProjectPort) {
        this.loadProjectPort = loadProjectPort;
        this.saveProjectPort = saveProjectPort;
    }

    @Override
    public TreatProjectResponse.Response treatProject(TreatProjectCommand command) {
        try {
            Project project = loadProjectPort.loadProject(new LoadProjectCommand(command.getProjectId()));
            if (project.getStatus() != ProjectStatus.PENDING) {
                return new TreatProjectResponse.ErrorResponse("Project cannot be treated if it is not pending");
            }
            project.treat(command.getStatus());

            Project modifiedProject = saveProjectPort.saveProject(new SaveProjectCommand(project));

            return new TreatProjectResponse.SuccessResponse(
                    new TreatProjectDtoResponse(
                            modifiedProject.getProjectId(),
                            modifiedProject.getStatus()
                    )
            );
        } catch (NoSuchElementException e) {
            return new TreatProjectResponse.ErrorResponse(e.getMessage());
        }
    }
}
