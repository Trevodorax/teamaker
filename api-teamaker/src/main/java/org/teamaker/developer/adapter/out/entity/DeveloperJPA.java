package org.teamaker.developer.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.technology.adapter.out.entity.SkillJPA;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEVELOPER")
public class DeveloperJPA {
    @Id
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String email;

    @Nonnull
    private LocalDate hiringDate;

    private LocalDate resignationDate;

    @OneToMany(mappedBy = "developer")
    private Set<TeamMembershipJPA> teamMemberships = new HashSet<>();

    @OneToMany(mappedBy = "developer")
    private Set<TeamChangeRequestJPA> teamChangeRequests = new HashSet<>();

    @OneToMany(mappedBy = "developer")
    private Set<SkillJPA> skills = new HashSet<>();
}
