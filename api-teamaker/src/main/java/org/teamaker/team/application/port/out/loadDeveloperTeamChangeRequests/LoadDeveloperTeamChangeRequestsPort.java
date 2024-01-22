package org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests;

import org.teamaker.team.domain.TeamChangeRequest;

import java.util.List;

public interface LoadDeveloperTeamChangeRequestsPort {
    /**
     * Retrieves all change requests ever made by a developer.
     *
     * @param command The developer to get the infos for
     * @return All the team change requests ever made by the developer
     */
    List<TeamChangeRequest> loadDeveloperTeamChangeRequests(LoadDeveloperTeamChangeRequestsCommand command) throws IllegalArgumentException;
}
