package org.teamaker.team.application.port.out.createTeamChangeRequest;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class CreateTeamChangeRequestCommand extends SelfValidating<CreateTeamChangeRequestCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String requestedProjectId;

    public CreateTeamChangeRequestCommand(String developerId, String requestedProjectId) {
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;

        this.validateSelf();
    }

    public String getDeveloperId() {
        return developerId;
    }

    public String getRequestedProjectId() {
        return requestedProjectId;
    }
}
