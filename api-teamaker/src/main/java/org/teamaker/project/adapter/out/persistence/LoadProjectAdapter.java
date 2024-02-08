package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

@Component
public class LoadProjectAdapter implements LoadProjectPort {
    private final ProjectRepository projectRepository;

public LoadProjectAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project loadProject(LoadProjectCommand command) throws NoSuchElementException {
        return projectRepository.findById(command.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found"))
                .toDomain();
    }
}
