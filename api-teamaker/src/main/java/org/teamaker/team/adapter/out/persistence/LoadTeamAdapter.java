package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.team.adapter.out.persistence.repository.TeamMembershipRepository;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.domain.Team;

import java.util.NoSuchElementException;

@Component
public class LoadTeamAdapter implements LoadTeamPort {
    private final ProjectRepository projectRepository;
    private final TeamMembershipRepository teamMembershipRepository;

    public LoadTeamAdapter(ProjectRepository projectRepository, TeamMembershipRepository teamMembershipRepository) {
        this.projectRepository = projectRepository;
        this.teamMembershipRepository = teamMembershipRepository;
    }

    @Override
    public Team loadTeam(LoadTeamCommand command) {
        ProjectJPA project = projectRepository.
                findById(command.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found"));

        return new Team(
                project.getId(),
                teamMembershipRepository
                        .findAllByProject(project)
                        .stream()
                        .map(teamMembershipJPA -> teamMembershipJPA.getDeveloper().toDomain())
                        .toList(),
                project.getIsLocked()
        );
    }
}
