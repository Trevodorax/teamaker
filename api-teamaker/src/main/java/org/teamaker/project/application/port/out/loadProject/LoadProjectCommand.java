package org.teamaker.project.application.port.out.loadProject;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class LoadProjectCommand extends SelfValidating<LoadProjectCommand> {
    @NotNull
    private final String projectId;

    public LoadProjectCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }
}
