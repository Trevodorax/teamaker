package org.teamaker.project.application.port.in.getProject;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class GetProjectCommand extends SelfValidating<GetProjectCommand> {
    @NotNull
    private final String projectId;

    public GetProjectCommand(String projectId) {
        this.projectId = projectId;

        this.validateSelf();
    }
}
