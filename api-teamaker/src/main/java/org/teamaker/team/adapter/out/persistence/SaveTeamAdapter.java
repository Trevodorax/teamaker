package org.teamaker.team.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipPK;
import org.teamaker.team.adapter.out.persistence.repository.TeamMembershipRepository;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SaveTeamAdapter implements SaveTeamPort {
    private final ProjectRepository projectRepository;
    private final TeamMembershipRepository teamMembershipRepository;
    private final DeveloperRepository developerRepository;

    public SaveTeamAdapter(ProjectRepository projectRepository, TeamMembershipRepository teamMembershipRepository, DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.teamMembershipRepository = teamMembershipRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    @Transactional
    public Team saveTeam(SaveTeamCommand command) throws NoSuchElementException {
        ProjectJPA projectJPA = projectRepository
                .findById(command.getTeam().getProjectId())
                .orElseThrow(() ->
                        new NoSuchElementException("Project not found with id " + command.getTeam().getProjectId()));

        projectJPA.setIsLocked(command.getTeam().isLocked());

        Set<TeamMembershipJPA> existingTeamMemberships = teamMembershipRepository
                .findByProjectId(command.getTeam()
                        .getProjectId());

        Set<String> currentDeveloperIds = existingTeamMemberships
                .stream()
                .map(tm -> tm.getDeveloper().getId())
                .collect(Collectors.toSet());

        Set<TeamMembershipJPA> updatedTeamMemberships = new HashSet<>(existingTeamMemberships);
        for (Developer developer : command.getTeam().getDevelopers()) {
            if (!currentDeveloperIds.contains(developer.getDeveloperId())) {
                DeveloperJPA developerJPA = developerRepository
                        .findById(developer.getDeveloperId())
                        .orElseThrow(() ->
                                new NoSuchElementException("Developer not found with id " + developer.getDeveloperId()));
                TeamMembershipJPA teamMembershipJPA = new TeamMembershipJPA();
                teamMembershipJPA.setId(new TeamMembershipPK(developerJPA.getId(), projectJPA.getId()));
                teamMembershipJPA.setProject(projectJPA);
                teamMembershipJPA.setDeveloper(developerJPA);
                teamMembershipJPA.setStartDate(LocalDate.now());
                teamMembershipJPA.setEndDate(projectJPA.getEndDate());

                teamMembershipRepository.save(teamMembershipJPA);

                updatedTeamMemberships.add(teamMembershipJPA);
            }
            // remove the developer from the currentDeveloperIds to not treat it again
            currentDeveloperIds.remove(developer.getDeveloperId());
        }
        projectJPA.setTeamMemberships(updatedTeamMemberships);
        projectRepository.save(projectJPA);

        // change the endDate of the team memberships of the developers that are not in the team anymore
//        existingTeamMemberships
//                .stream()
//                .filter(tm -> currentDeveloperIds.contains(tm.getDeveloper().getId()))
//                .forEach(tm -> {
//                    tm.setEndDate(LocalDate.now());
//                    teamMembershipRepository.save(tm);
//                });

        // remove the developers that are not in the team anymore
        teamMembershipRepository.deleteAll(
                existingTeamMemberships
                        .stream()
                        .filter(tm -> currentDeveloperIds.contains(tm.getDeveloper().getId()))
                        .collect(Collectors.toSet())
        );

        return projectJPA.toDomain().getTeam();
    }
}
