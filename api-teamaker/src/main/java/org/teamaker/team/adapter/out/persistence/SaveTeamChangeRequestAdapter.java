package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.persistence.repository.TeamChangeRequestRepository;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

import java.util.NoSuchElementException;

@Component
public class SaveTeamChangeRequestAdapter implements SaveTeamChangeRequestPort {
    private final TeamChangeRequestRepository teamChangeRequestRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    public SaveTeamChangeRequestAdapter(TeamChangeRequestRepository teamChangeRequestRepository, DeveloperRepository developerRepository, ProjectRepository projectRepository) {
        this.teamChangeRequestRepository = teamChangeRequestRepository;
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public TeamChangeRequest saveTeamChangeRequest(SaveTeamChangeRequestCommand command) throws NoSuchElementException {
        TeamChangeRequestJPA teamChangeRequestJPA = teamChangeRequestRepository
                .findByRequestId(command.getTeamChangeRequest().getId())
                .orElseThrow(() ->
                        new NoSuchElementException("Team change request not found with id " + command.getTeamChangeRequest().getId()));

        teamChangeRequestJPA.setStatus(command.getTeamChangeRequest().getStatus());

        DeveloperJPA developerJPA = developerRepository
                .findById(command.getTeamChangeRequest().getDeveloperId())
                .orElseThrow(() ->
                        new NoSuchElementException("Developer not found with id " + command.getTeamChangeRequest().getDeveloperId()));
        teamChangeRequestJPA.setDeveloper(developerJPA);

        ProjectJPA fromProjectJPA = projectRepository
                .findById(command.getTeamChangeRequest().getFromProjectId())
                .orElseThrow(() ->
                        new NoSuchElementException("Project not found with id " + command.getTeamChangeRequest().getFromProjectId()));
        teamChangeRequestJPA.setCurrentProject(fromProjectJPA);

        ProjectJPA toProjectJPA = projectRepository
                .findById(command.getTeamChangeRequest().getToProjectId())
                .orElseThrow(() ->
                        new NoSuchElementException("Project not found with id " + command.getTeamChangeRequest().getToProjectId()));
        teamChangeRequestJPA.setRequestedProject(toProjectJPA);

        teamChangeRequestJPA.setDate(command.getTeamChangeRequest().getSubmitDate());

        return teamChangeRequestRepository.save(teamChangeRequestJPA).toDomain();
    }
}
