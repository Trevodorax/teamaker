package org.teamaker.team.application.port.out.saveTeam;

import org.teamaker.team.domain.Team;

import java.util.NoSuchElementException;

public interface SaveTeamPort {
    /**
     * Save a team
     * @param command The command to save the team
     * @return The saved team
     * @throws NoSuchElementException if the project doesn't exist
     */
    Team saveTeam(SaveTeamCommand command) throws NoSuchElementException;
}
