package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.SubmitProjectCommand;
import org.teamaker.project.application.port.in.SubmitProjectUseCase;
import org.teamaker.project.domain.Project;

@Component
class SubmitProjectService implements SubmitProjectUseCase {
    @Override
    public Project submitProject(SubmitProjectCommand command) {
        // TODO: create the project
        // TODO: create the team and assign the devs to the project
        return null;
    }
}
