package org.teamaker.team.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.project.adapter.out.entity.ProjectJPA;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAM_MEMBERSHIP")
public class TeamMembershipJPA {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private TeamMembershipPK id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectJPA project;

    @ManyToOne
    @MapsId("developerId")
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private DeveloperJPA developer;

    @Nonnull
    private LocalDate startDate;

    private LocalDate endDate;
}
