package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.team.adapter.out.persistence.repository.TeamMembershipRepository;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectPort;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LoadPossibleDevelopersForProjectAdapter implements LoadPossibleDevelopersForProjectPort {
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    public LoadPossibleDevelopersForProjectAdapter(ProjectRepository projectRepository, DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    public List<Developer> loadPossibleDevelopersForProject(LoadPossibleDevelopersForProjectCommand command) throws NoSuchElementException {
        ProjectJPA project = projectRepository
                .findById(command.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found with id " + command.getProjectId()));

        List<DeveloperJPA> developers = developerRepository.findAvailableDevelopersWithRequiredTechnologies(project.getStartDate(), project.getEndDate(), project.getId());

        return developers
                .stream()
                .map(DeveloperJPA::toDomain)
                .toList();
    }
}
