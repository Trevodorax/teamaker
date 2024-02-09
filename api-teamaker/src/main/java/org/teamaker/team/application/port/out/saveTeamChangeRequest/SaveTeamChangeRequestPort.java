package org.teamaker.team.application.port.out.saveTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

import java.util.NoSuchElementException;

public interface SaveTeamChangeRequestPort {
    /**
     * Save a team change request
     * @param command the command
     * @return the saved team change request
     * @throws NoSuchElementException if the team change request is not found
     */
    TeamChangeRequest saveTeamChangeRequest(SaveTeamChangeRequestCommand command) throws NoSuchElementException;
}
