package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class SubmitTeamChangeRequestCommand extends SelfValidating<SubmitTeamChangeRequestCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String requestedProjectId;
    @NotNull
    private final String fromProjectId;

    public SubmitTeamChangeRequestCommand(String developerId, String requestedProjectId, String fromProjectId) {
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;
        this.fromProjectId = fromProjectId;

        this.validateSelf();
    }
}
