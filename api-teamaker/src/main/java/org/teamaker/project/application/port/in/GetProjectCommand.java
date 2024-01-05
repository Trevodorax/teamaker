package org.teamaker.project.application.port.in;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetProjectCommand extends SelfValidating<GetProjectCommand> {
    @NotNull
    private final String projectId;

    public GetProjectCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }
}
