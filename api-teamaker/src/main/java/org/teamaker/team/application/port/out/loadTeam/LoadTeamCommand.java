package org.teamaker.team.application.port.out.loadTeam;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadTeamCommand extends SelfValidating<LoadTeamCommand> {
    @NotNull
    private final String projectId;

    public LoadTeamCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }
}
