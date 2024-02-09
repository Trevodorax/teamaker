package org.teamaker.team.application.port.out.createTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface CreateTeamChangeRequestPort {
    /**
     * Create a team change request
     * @param command the command to create a team change request
     * @return the created team change request
     * @throws IllegalArgumentException if the developer or the projects are not found
     *          or if the developer is already in the requested project
     *          or if the developer is not in the from project
     */
    TeamChangeRequest createTeamChangeRequest(CreateTeamChangeRequestCommand command) throws IllegalArgumentException;
}
