package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.domain.Project;

import java.util.List;

@Component
public class LoadProjectsAdapter implements LoadProjectsPort {
    private final ProjectRepository projectRepository;

    public LoadProjectsAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> loadProjects() {
        return projectRepository
                .findAll()
                .stream()
                .map(ProjectJPA::toDomain)
                .toList();
    }
}
