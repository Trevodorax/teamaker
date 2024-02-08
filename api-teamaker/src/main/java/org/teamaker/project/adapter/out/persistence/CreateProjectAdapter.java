package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.project.application.port.out.createProject.CreateProjectCommand;
import org.teamaker.project.application.port.out.createProject.CreateProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.team.adapter.out.persistence.repository.TeamMembershipRepository;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.adapter.out.entity.TechnologyRequirementJPA;
import org.teamaker.technology.adapter.out.entity.TechnologyRequirementPK;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRequirementRepository;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Component
public class CreateProjectAdapter implements CreateProjectPort {
    private final ProjectRepository projectRepository;
    private final TechnologyRequirementRepository technologyRequirementRepository;
    private final TechnologyRepository technologyRepository;
    private final TeamMembershipRepository teamMembershipRepository;

    public CreateProjectAdapter(ProjectRepository projectRepository, TechnologyRequirementRepository technologyRequirementRepository, TechnologyRepository technologyRepository, TeamMembershipRepository teamMembershipRepository) {
        this.projectRepository = projectRepository;
        this.technologyRequirementRepository = technologyRequirementRepository;
        this.technologyRepository = technologyRepository;
        this.teamMembershipRepository = teamMembershipRepository;
    }

    @Transactional
    @Override
    public Project createProject(CreateProjectCommand command) {
        String id = UUID.randomUUID().toString();
        while (projectRepository.existsById(id)) {
            id = UUID.randomUUID().toString();
        }

        ProjectJPA projectJPA = new ProjectJPA();
        projectJPA.setId(id);
        projectJPA.setName(command.getName());
        projectJPA.setDescription(command.getDescription());
        projectJPA.setStatus(ProjectStatus.PENDING);
        projectJPA.setPriority(command.getPriority());
        projectJPA.setStartDate(command.getStartDate());
        projectJPA.setEndDate(command.getEndDate());
        projectJPA.setIsLocked(false);
        projectRepository.save(projectJPA);

        Set<TechnologyRequirementJPA> technologyRequirements = new HashSet<>();

        command.getTechnologies().forEach((technologyId, devsNeeded) -> {
            TechnologyJPA technologyJPA = technologyRepository
                    .findById(technologyId)
                    .orElseThrow(() -> new NoSuchElementException("Technology with id " + technologyId + " not found"));

            TechnologyRequirementJPA technologyRequirementJPA = new TechnologyRequirementJPA();
            TechnologyRequirementPK technologyRequirementPK = new TechnologyRequirementPK(projectJPA.getId(), technologyId);
            technologyRequirementJPA.setTechnologyRequirementPK(technologyRequirementPK);
            technologyRequirementJPA.setProject(projectJPA);
            technologyRequirementJPA.setTechnology(technologyJPA);
            technologyRequirementJPA.setDevsNeeded(devsNeeded);
            technologyRequirementRepository.save(technologyRequirementJPA);
            technologyRequirements.add(technologyRequirementJPA);
        });
        projectJPA.setTechnologyRequirements(technologyRequirements);

        Set<TeamMembershipJPA> teamMemberships = teamMembershipRepository.findAllByProject(projectJPA);
        projectJPA.setTeamMemberships(teamMemberships);

        projectRepository.save(projectJPA);
        return projectJPA.toDomain();
    }
}
