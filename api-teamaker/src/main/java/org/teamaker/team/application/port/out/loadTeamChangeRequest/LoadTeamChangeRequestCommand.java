package org.teamaker.team.application.port.out.loadTeamChangeRequest;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadTeamChangeRequestCommand extends SelfValidating<LoadTeamChangeRequestCommand> {
    @NotNull
    private final String requestId;

    public LoadTeamChangeRequestCommand(String requestId) {
        this.requestId = requestId;

        this.validateSelf();
    }

    public String getRequestId() {
        return requestId;
    }
}
