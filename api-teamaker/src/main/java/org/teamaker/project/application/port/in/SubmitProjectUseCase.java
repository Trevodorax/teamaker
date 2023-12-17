package org.teamaker.project.application.port.in;

import org.teamaker.project.domain.Project;

public interface SubmitProjectUseCase {
    public Project submitProject(SubmitProjectCommand command);
}
