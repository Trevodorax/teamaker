package org.teamaker.team.application.port.out.saveTeam;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.team.domain.Team;

import javax.validation.constraints.NotNull;

@Getter
public class SaveTeamCommand extends SelfValidating<SaveTeamCommand> {
    @NotNull
    private final Team team;

    public SaveTeamCommand(Team team) {
        this.team = team;
        this.validateSelf();
    }
}
