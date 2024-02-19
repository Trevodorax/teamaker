package org.teamaker.team.application.port.out.saveTeamChangeRequest;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.team.domain.TeamChangeRequest;

import javax.validation.constraints.NotNull;

@Getter
public class SaveTeamChangeRequestCommand extends SelfValidating<SaveTeamChangeRequestCommand> {
    @NotNull
    private final TeamChangeRequest teamChangeRequest;

    public SaveTeamChangeRequestCommand(TeamChangeRequest teamChangeRequest) {
        this.teamChangeRequest = teamChangeRequest;
        this.validateSelf();
    }
}
