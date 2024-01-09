package org.teamaker.team.application.port.in.getTeam;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class GetTeamCommand extends SelfValidating<GetTeamCommand> {
    @NotNull
    private final String id;

    public GetTeamCommand(String id) {
        this.id = id;

        this.validateSelf();
    }

    public String getId() {
        return id;
    }
}
