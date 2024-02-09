package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.domain.Team;

import java.util.NoSuchElementException;

@Component
public class LoadTeamAdapter implements LoadTeamPort {
    private final ProjectRepository projectRepository;

    public LoadTeamAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Team loadTeam(LoadTeamCommand command) {
        ProjectJPA project = projectRepository.
                findById(command.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found"));

        return project.toDomain().getTeam();

    }
}
