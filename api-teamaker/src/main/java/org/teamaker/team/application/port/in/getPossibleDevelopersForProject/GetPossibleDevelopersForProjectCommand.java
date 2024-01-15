package org.teamaker.team.application.port.in.getPossibleDevelopersForProject;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetPossibleDevelopersForProjectCommand extends SelfValidating<GetPossibleDevelopersForProjectCommand> {
    @NotNull
    private final String projectId;

    public GetPossibleDevelopersForProjectCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }
}
