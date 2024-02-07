package org.teamaker.project.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.technology.adapter.out.entity.TechnologyRequirementJPA;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT")
public class ProjectJPA {
    @Id
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

    @Nonnull
    private LocalDate endDate;

    @OneToMany(mappedBy = "project")
    private Set<TeamMembershipJPA> teamMemberships = new HashSet<>();

    @OneToMany(mappedBy = "requestedProject")
    private Set<TeamChangeRequestJPA> requestedTeamChangeRequests = new HashSet<>();

    @OneToMany(mappedBy = "currentProject")
    private Set<TeamChangeRequestJPA> currentTeamChangeRequests = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<TechnologyRequirementJPA> technologyRequirements = new HashSet<>();
}
