package org.teamaker.team.application.port.out.loadTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface LoadTeamChangeRequestPort {
    /**
     * Retrieves a specific team change request
     *
     * @param command The id of the requested team change request
     * @return The team change request
     * @throws IllegalArgumentException If the team change request is not found
     */
    TeamChangeRequest loadTeamChangeRequest(LoadTeamChangeRequestCommand command) throws IllegalArgumentException;
}
