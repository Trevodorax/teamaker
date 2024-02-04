package org.teamaker.team.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.project.adapter.out.persistence.ProjectJPA;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAM_MEMBERSHIP")
public class TeamMembershipJPA {
    @EmbeddedId
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
