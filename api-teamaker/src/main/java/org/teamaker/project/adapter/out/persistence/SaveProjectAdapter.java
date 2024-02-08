package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;

@Component
public class SaveProjectAdapter implements SaveProjectPort {
    @Override
    public Project saveProject(SaveProjectCommand command) throws IllegalArgumentException {
        return null;
    }
}
