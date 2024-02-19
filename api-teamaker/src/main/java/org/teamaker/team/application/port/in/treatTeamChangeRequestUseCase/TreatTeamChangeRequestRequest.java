package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.team.domain.TreatTeamStatus;

import javax.validation.constraints.NotNull;

@Getter
public class TreatTeamChangeRequestRequest extends SelfValidating<TreatTeamChangeRequestRequest> {
    @NotNull
    private final TreatTeamStatus status;

    @JsonCreator
    public TreatTeamChangeRequestRequest(@JsonProperty("status") TreatTeamStatus status) {
        this.status = status;

        this.validateSelf();
    }
}
