package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.team.domain.TreatTeamStatus;

import javax.validation.constraints.NotNull;

@Getter
public class TreatTeamChangeRequestCommand extends SelfValidating<TreatTeamChangeRequestCommand> {
    @NotNull
    private final String teamChangeRequestId;

    @NotNull
    private final TreatTeamStatus status;

    public TreatTeamChangeRequestCommand(String teamChangeRequestId, TreatTeamStatus status) {
        this.teamChangeRequestId = teamChangeRequestId;
        this.status = status;

        this.validateSelf();
    }
}
