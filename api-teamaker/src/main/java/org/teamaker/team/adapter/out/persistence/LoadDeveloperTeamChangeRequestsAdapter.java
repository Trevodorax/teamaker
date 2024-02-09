package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.persistence.repository.TeamChangeRequestRepository;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsCommand;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsPort;
import org.teamaker.team.domain.TeamChangeRequest;

import java.util.List;

@Component
public class LoadDeveloperTeamChangeRequestsAdapter implements LoadDeveloperTeamChangeRequestsPort {
    private final TeamChangeRequestRepository teamChangeRequestRepository;

    public LoadDeveloperTeamChangeRequestsAdapter(TeamChangeRequestRepository teamChangeRequestRepository) {
        this.teamChangeRequestRepository = teamChangeRequestRepository;
    }

    @Override
    public List<TeamChangeRequest> loadDeveloperTeamChangeRequests(LoadDeveloperTeamChangeRequestsCommand command) throws IllegalArgumentException {
        List<TeamChangeRequestJPA> teamChangeRequestJPAS = teamChangeRequestRepository.findAllByDeveloperId(command.getDeveloperId());
        return teamChangeRequestJPAS.stream()
                .map(TeamChangeRequestJPA::toDomain)
                .toList();
    }
}
