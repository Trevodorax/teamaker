package org.teamaker.project.application.port.in.updateProjectInfo;

import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

public class UpdateProjectInfoCommand extends SelfValidating<UpdateProjectInfoCommand> {
    @NotNull
    private final String projectId;
    private final String name;
    private final String description;
    private final ProjectPriority priority;

    public UpdateProjectInfoCommand(String projectId, String name, String description, ProjectPriority priority) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.validateSelf();
    }

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectPriority getPriority() {
        return priority;
    }
}
