package org.teamaker.project.adapter.out.persistence.mariadb;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.persistence.ProjectRepository;
import org.teamaker.project.application.port.out.CreateProjectCommand;
import org.teamaker.project.domain.Project;

@Component
public class DBProjectRepository implements ProjectRepository {
    @Override
    public Project createProject(CreateProjectCommand command) {
        // TODO : create the project
        return null;
    }
}
