package org.teamaker.team.application.port.in.getPossibleDevelopersForProject;

public interface GetPossibleDevelopersForProjectUseCase {
    GetPossibleDevelopersForTeamResponse.Response getPossibleDevelopersForProject(GetPossibleDevelopersForProjectCommand command);
}
