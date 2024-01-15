package org.teamaker.team.application.port.in.removeDeveloperFromTeam;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class RemoveDeveloperFromTeamCommand extends SelfValidating<RemoveDeveloperFromTeamCommand> {
    @NotNull
    private final String developerId;

    @NotNull
    private final String projectId;

    public RemoveDeveloperFromTeamCommand(String developerId, String projectId) {
        this.developerId = developerId;
        this.projectId = projectId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }

    public String getProjectId() {
        return projectId;
    }
}
