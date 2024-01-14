package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class SubmitTeamChangeRequestCommand extends SelfValidating<SubmitTeamChangeRequestCommand> {
    @NotNull
    private final String developerId;

    @NotNull
    private final String requestedProjectId;

    public SubmitTeamChangeRequestCommand(String developerId, String requestedProjectId) {
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
