package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.adapter.out.persistence.repository.TeamChangeRequestRepository;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

@Component
public class LoadTeamChangeRequestAdapter implements LoadTeamChangeRequestPort {
    private final TeamChangeRequestRepository teamChangeRequestRepository;

    public LoadTeamChangeRequestAdapter(TeamChangeRequestRepository teamChangeRequestRepository) {
        this.teamChangeRequestRepository = teamChangeRequestRepository;
    }

    @Override
    public TeamChangeRequest loadTeamChangeRequest(LoadTeamChangeRequestCommand command) throws IllegalArgumentException {
        return teamChangeRequestRepository
                .findByRequestId(command.getRequestId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Team change request not found"))
                .toDomain();
    }
}
