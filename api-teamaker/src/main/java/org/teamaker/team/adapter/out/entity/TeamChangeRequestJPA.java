package org.teamaker.team.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAM_CHANGE_REQUEST")
public class TeamChangeRequestJPA {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private TeamChangeRequestPK id;

    @Nonnull
    private String requestId;

    @ManyToOne
    @MapsId("currentProjectId")
    @JoinColumn(name = "current_project_id", referencedColumnName = "id")
    private ProjectJPA currentProject;

    @ManyToOne
    @MapsId("developerId")
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private DeveloperJPA developer;

    @ManyToOne
    @JoinColumn(name = "requested_project_id", referencedColumnName = "id")
    private ProjectJPA requestedProject;

    @Nonnull
    private LocalDate date;

    @Nonnull
    @Enumerated(EnumType.STRING)
    private TeamRequestStatus status;

    public TeamChangeRequest toDomain() {
        return new TeamChangeRequest(
                requestId,
                developer.getId(),
                currentProject.getId(),
                requestedProject.getId(),
                status,
                date
        );
    }
}
