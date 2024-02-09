package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestPK;
import org.teamaker.team.adapter.out.persistence.repository.TeamChangeRequestRepository;
import org.teamaker.team.adapter.out.persistence.repository.TeamMembershipRepository;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class CreateTeamChangeRequestAdapter implements CreateTeamChangeRequestPort {
    private final TeamChangeRequestRepository teamChangeRequestRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;
    private final TeamMembershipRepository teamMembershipRepository;

    public CreateTeamChangeRequestAdapter(TeamChangeRequestRepository teamChangeRequestRepository, DeveloperRepository developerRepository, ProjectRepository projectRepository, TeamMembershipRepository teamMembershipRepository) {
        this.teamChangeRequestRepository = teamChangeRequestRepository;
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
        this.teamMembershipRepository = teamMembershipRepository;
    }

    @Override
    @Transactional
    public TeamChangeRequest createTeamChangeRequest(CreateTeamChangeRequestCommand command) throws IllegalArgumentException {
        TeamChangeRequestJPA teamChangeRequestJPA = new TeamChangeRequestJPA();
        DeveloperJPA developerJPA = developerRepository
                .findById(command.getDeveloperId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Developer with id " + command.getDeveloperId() + " not found."));
        ProjectJPA requestedProjectJPA = projectRepository
                .findById(command.getRequestedProjectId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Requested project with id " + command.getRequestedProjectId() + " not found."));
        ProjectJPA fromProjectJPA = projectRepository
                .findById(command.getFromProjectId())
                .orElseThrow(() ->
                        new IllegalArgumentException("To project with id " + command.getFromProjectId() + " not found."));

        // check if the developer is already in the requested project
        if (teamMembershipRepository.existsByDeveloperAndProject(developerJPA, requestedProjectJPA)) {
            throw new IllegalArgumentException("Developer is already in the requested project.");
        }

        // check if the developer is not in the from project
        if (!teamMembershipRepository.existsByDeveloperAndProject(developerJPA, fromProjectJPA)) {
            throw new IllegalArgumentException("Developer is not in the from project.");
        }

        teamChangeRequestJPA.setId(new TeamChangeRequestPK(fromProjectJPA.getId(), developerJPA.getId()));

        String requestId = UUID.randomUUID().toString();
        while (teamChangeRequestRepository.existsByRequestId(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        teamChangeRequestJPA.setRequestId(requestId);
        teamChangeRequestJPA.setDeveloper(developerJPA);
        teamChangeRequestJPA.setRequestedProject(requestedProjectJPA);
        teamChangeRequestJPA.setCurrentProject(fromProjectJPA);
        teamChangeRequestJPA.setStatus(TeamRequestStatus.PENDING);
        teamChangeRequestJPA.setDate(LocalDate.now());

        return teamChangeRequestRepository.save(teamChangeRequestJPA).toDomain();
    }
}
