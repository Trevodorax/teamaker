package org.teamaker.team.application.port.in.getTeam;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetTeamCommand extends SelfValidating<GetTeamCommand> {
    @NotNull
    private final String projectId;

    public GetTeamCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }
}
