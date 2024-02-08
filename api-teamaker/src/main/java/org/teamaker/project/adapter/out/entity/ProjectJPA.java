package org.teamaker.project.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.team.domain.Team;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.adapter.out.entity.TechnologyRequirementJPA;
import org.teamaker.technology.domain.Technology;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT")
public class ProjectJPA {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Nonnull
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    private ProjectPriority priority;

    @Nonnull
    private LocalDate startDate;

    private LocalDate endDate;

    @Nonnull
    private Boolean isLocked;

    @OneToMany(mappedBy = "project")
    private Set<TeamMembershipJPA> teamMemberships;

    @OneToMany(mappedBy = "requestedProject")
    private Set<TeamChangeRequestJPA> requestedTeamChangeRequests;

    @OneToMany(mappedBy = "currentProject")
    private Set<TeamChangeRequestJPA> currentTeamChangeRequests;

    @OneToMany(mappedBy = "project")
    private Set<TechnologyRequirementJPA> technologyRequirements;

    private Team getTeam() {
        if (teamMemberships == null) {
            return new Team(id, new ArrayList<>(), isLocked);
        }
        List<Developer> developers = getTeamMemberships().stream()
                .map(TeamMembershipJPA::getDeveloper)
                .map(DeveloperJPA::toDomain)
                .toList();
        return new Team(id, developers, isLocked);
    }

    private Map<Technology, Integer> getTechnologies() {
        Map<Technology, Integer> technologies = new HashMap<>();
        for (TechnologyRequirementJPA technologyRequirement : technologyRequirements) {
            Technology technology = technologyRequirement.getTechnology().toDomain();
            Integer devsNeeded = technologyRequirement.getDevsNeeded();
            technologies.put(technology, devsNeeded);
        }
        return technologies;
    }

    public Project toDomain() {
        return new Project(
                id,
                name,
                description,
                priority,
                status,
                startDate,
                endDate,
                getTeam(),
                getTechnologies()
        );
    }
}
