package org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadDeveloperTeamChangeRequestsCommand extends SelfValidating<LoadDeveloperTeamChangeRequestsCommand> {
    @NotNull
    private final String developerId;

    public LoadDeveloperTeamChangeRequestsCommand(String developerId) {
        this.developerId = developerId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }
}
