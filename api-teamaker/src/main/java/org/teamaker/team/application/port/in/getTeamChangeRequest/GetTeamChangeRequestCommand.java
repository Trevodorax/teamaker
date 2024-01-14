package org.teamaker.team.application.port.in.getTeamChangeRequest;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetTeamChangeRequestCommand extends SelfValidating<GetTeamChangeRequestCommand> {
    @NotNull
    private final String teamChangeRequestId;

    public GetTeamChangeRequestCommand(String teamChangeRequestId) {
        this.teamChangeRequestId = teamChangeRequestId;

        this.validateSelf();
    }

    public String getTeamChangeRequestId() {
        return teamChangeRequestId;
    }
}
