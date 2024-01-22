package org.teamaker.team.application.port.out.loadPossibleDevelopersForProject;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadPossibleDevelopersForProjectCommand extends SelfValidating<LoadPossibleDevelopersForProjectCommand> {
    @NotNull
    private final String projectId;

    public LoadPossibleDevelopersForProjectCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }
}
