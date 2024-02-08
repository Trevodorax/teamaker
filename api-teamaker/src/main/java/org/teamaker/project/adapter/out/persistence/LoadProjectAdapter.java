package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;

@Component
public class LoadProjectAdapter implements LoadProjectPort {
    @Override
    public Project loadProject(LoadProjectCommand command) throws IllegalArgumentException {
        return null;
    }
}
