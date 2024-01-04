package org.teamaker.team.application.port.out;

import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.team.application.port.in.GetTeamCommand;

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
