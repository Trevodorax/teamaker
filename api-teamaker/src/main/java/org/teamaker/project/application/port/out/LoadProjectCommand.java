package org.teamaker.project.application.port.out;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadProjectCommand extends SelfValidating<LoadProjectCommand> {
    @NotNull
    private final String projectId;

    public LoadProjectCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }
}
