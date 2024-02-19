package org.teamaker.team.application.port.out.createTeamChangeRequest;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class CreateTeamChangeRequestCommand extends SelfValidating<CreateTeamChangeRequestCommand> {
    @NotNull
    private final String developerId;
    @NotNull
    private final String requestedProjectId;
    @NotNull
    private final String fromProjectId;

    public CreateTeamChangeRequestCommand(String developerId, String requestedProjectId, String toProjectId) {
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;
        this.fromProjectId = toProjectId;

        this.validateSelf();
    }
}
