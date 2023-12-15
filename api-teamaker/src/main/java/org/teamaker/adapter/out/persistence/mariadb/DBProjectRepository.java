package org.teamaker.adapter.out.persistence.mariadb;

import org.springframework.stereotype.Component;
import org.teamaker.adapter.out.persistence.ProjectRepository;
import org.teamaker.domain.Project;
@Component
public class DBProjectRepository implements ProjectRepository {
    @Override
    public Project loadProject(String projectId) {
        return new Project("test-project");
    }
}
