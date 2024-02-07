package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.domain.Project;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.team.adapter.out.persistence.repository.TeamMembershipRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LoadDeveloperProjectsAdapter implements LoadDeveloperProjectsPort {
    private final DeveloperRepository developerRepository;
    private final TeamMembershipRepository teamMembershipRepository;

    public LoadDeveloperProjectsAdapter(DeveloperRepository developerRepository, TeamMembershipRepository teamMembershipRepository) {
        this.developerRepository = developerRepository;
        this.teamMembershipRepository = teamMembershipRepository;
    }

    @Override
    public List<Project> loadDeveloperProjects(LoadDeveloperProjectsCommand command) throws NoSuchElementException {
        DeveloperJPA developer = developerRepository
                .findById(command.getDeveloperId())
                .orElseThrow(()
                        -> new NoSuchElementException("Developer not found"));

        List<TeamMembershipJPA> teamMemberships = teamMembershipRepository.findByDeveloper(developer);

        return teamMemberships.stream()
                .map(TeamMembershipJPA::getProject)
                .map(ProjectJPA::toDomain)
                .toList();
    }
}
