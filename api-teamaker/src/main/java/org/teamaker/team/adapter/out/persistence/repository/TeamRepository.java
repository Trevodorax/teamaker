package org.teamaker.team.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.teamaker.developer.domain.Developer;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestPort;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsCommand;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsPort;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.out.loadPossibleDevelopersForProject.LoadPossibleDevelopersForProjectPort;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestPort;
import org.teamaker.team.domain.Team;
import org.teamaker.team.domain.TeamChangeRequest;

import java.util.List;

@Component
public interface TeamRepository extends
        JpaRepository<TeamChangeRequestJPA, String>,
        CreateTeamChangeRequestPort,
        LoadDeveloperTeamChangeRequestsPort,
        LoadPossibleDevelopersForProjectPort,
        LoadTeamPort,
        LoadTeamChangeRequestPort,
        SaveTeamPort,
        SaveTeamChangeRequestPort {
    @Override
    default TeamChangeRequest createTeamChangeRequest(CreateTeamChangeRequestCommand command) {
        return null;
    }

    @Override
    default List<TeamChangeRequest> loadDeveloperTeamChangeRequests(LoadDeveloperTeamChangeRequestsCommand command) throws IllegalArgumentException {
        return null;
    }

    @Override
    default List<Developer> loadPossibleDevelopersForProject(LoadPossibleDevelopersForProjectCommand command) throws IllegalArgumentException {
        return null;
    }

    @Override
    default Team loadTeam(LoadTeamCommand command) {
        return null;
    }

    @Override
    default TeamChangeRequest loadTeamChangeRequest(LoadTeamChangeRequestCommand command) throws IllegalArgumentException {
        return null;
    }

    @Override
    default Team saveTeam(Team team) {
        return null;
    }

    @Override
    default TeamChangeRequest saveTeamChangeRequest(TeamChangeRequest teamChangeRequest) {
        return null;
    }
}
