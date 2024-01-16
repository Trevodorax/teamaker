package org.teamaker.team.application;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamCommand;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamUseCase;

public class AssignDeveloperToTeamService implements AssignDeveloperToTeamUseCase {
    @Override
    public DeveloperResponse assignDeveloperToTeam(AssignDeveloperToTeamCommand command) {
        // check if developer is free (create UC in developer + add it to the sheets + call it here)
        // assign him in the project if he's available
        // send back the full developer

        return null;
    }
}
