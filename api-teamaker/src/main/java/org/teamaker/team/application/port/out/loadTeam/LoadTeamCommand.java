package org.teamaker.team.application.port.out.loadTeam;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class LoadTeamCommand extends SelfValidating<LoadTeamCommand> {
    @NotNull
    private final String id;

    public LoadTeamCommand(String id) {
        this.id = id;

        this.validateSelf();
    }

    public String getId() {
        return id;
    }
}
