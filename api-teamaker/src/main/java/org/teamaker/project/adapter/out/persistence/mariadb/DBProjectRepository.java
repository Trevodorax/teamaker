package org.teamaker.project.adapter.out.persistence.mariadb;

import org.teamaker.project.adapter.out.persistence.ProjectRepository;
import org.teamaker.project.application.port.out.CreateProjectCommand;
import org.teamaker.project.domain.Project;

public class DBProjectRepository implements ProjectRepository {
    @Override
    public Project createProject(CreateProjectCommand command) {
        // TODO : create the project
        return null;
    }
}
