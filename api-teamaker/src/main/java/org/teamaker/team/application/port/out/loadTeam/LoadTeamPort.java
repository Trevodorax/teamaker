package org.teamaker.team.application.port.out.loadTeam;

import org.teamaker.team.domain.Team;

import java.util.NoSuchElementException;

public interface LoadTeamPort {
    /**
     * Load a team by its project id
     * @param command the command to load the team
     * @return the team
     * @throws NoSuchElementException if the team is not found
     */
    Team loadTeam(LoadTeamCommand command) throws NoSuchElementException;
}
