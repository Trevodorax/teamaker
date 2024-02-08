package org.teamaker.developer.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.developer.domain.Developer;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.technology.adapter.out.entity.SkillJPA;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEVELOPER")
public class DeveloperJPA {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    @Column(unique = true)
    private String email;

    @Nonnull
    private LocalDate hiringDate;

    private LocalDate resignationDate;

    @OneToMany(mappedBy = "developer")
    private Set<TeamMembershipJPA> teamMemberships;

    @OneToMany(mappedBy = "developer")
    private Set<TeamChangeRequestJPA> teamChangeRequests;

    @OneToMany(mappedBy = "developer")
    private Set<SkillJPA> skills;

    public Developer toDomain() {
        return new Developer(id, name, email, hiringDate, resignationDate);
    }
}
