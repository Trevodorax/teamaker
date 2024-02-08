package org.teamaker.project.application.port.out.saveProject;

import lombok.Getter;
import org.teamaker.project.domain.Project;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class SaveProjectCommand extends SelfValidating<SaveProjectCommand> {
    @NotNull
    private final Project project;

    public SaveProjectCommand(Project project) {
        this.project = project;
        this.validateSelf();
    }
}
