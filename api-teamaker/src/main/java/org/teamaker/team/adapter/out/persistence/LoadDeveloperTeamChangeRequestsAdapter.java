package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsCommand;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsPort;
import org.teamaker.team.domain.TeamChangeRequest;

import java.util.List;

@Component
public class LoadDeveloperTeamChangeRequestsAdapter implements LoadDeveloperTeamChangeRequestsPort {
    @Override
    public List<TeamChangeRequest> loadDeveloperTeamChangeRequests(LoadDeveloperTeamChangeRequestsCommand command) throws IllegalArgumentException {
        return null;
    }
}
