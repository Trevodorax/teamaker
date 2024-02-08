package org.teamaker.project.application.port.in.treatProject;

import lombok.Getter;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class TreatProjectCommand extends SelfValidating<TreatProjectCommand> {
    @NotNull
    private final String projectId;

    @NotNull
    private final ProjectStatus status;

    public TreatProjectCommand(String projectId, ProjectStatus status) {
        this.projectId = projectId;
        this.status = status;
        this.validateSelf();
    }
}
