package org.teamaker.team.application.port.in.getTeam;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetTeamCommand extends SelfValidating<GetTeamCommand> {
    @NotNull
    private final String teamId;

    public GetTeamCommand(String teamId) {
        this.teamId = teamId;

        this.validateSelf();
    }

    public String getTeamId() {
        return teamId;
    }
}
