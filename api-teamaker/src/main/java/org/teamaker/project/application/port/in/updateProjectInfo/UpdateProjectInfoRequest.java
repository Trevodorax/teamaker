package org.teamaker.project.application.port.in.updateProjectInfo;

import lombok.Getter;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.shared.validation.SelfValidating;

@Getter
public class UpdateProjectInfoRequest extends SelfValidating<UpdateProjectInfoRequest> {
    private final String name;
    private final String description;
    private final ProjectPriority priority;

    public UpdateProjectInfoRequest(String name, String description, ProjectPriority priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.validateSelf();
    }
}
