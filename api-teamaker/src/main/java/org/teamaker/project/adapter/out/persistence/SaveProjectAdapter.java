package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;

import java.util.NoSuchElementException;

@Component
public class SaveProjectAdapter implements SaveProjectPort {
    private final ProjectRepository projectRepository;

    public SaveProjectAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveProject(SaveProjectCommand command) throws NoSuchElementException {
        ProjectJPA projectJPA = projectRepository
                .findById(command.getProject().getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        projectJPA.updateFromDomain(command.getProject());
        return projectRepository.save(projectJPA).toDomain();
    }
}
