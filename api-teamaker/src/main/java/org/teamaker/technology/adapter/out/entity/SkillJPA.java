package org.teamaker.technology.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SKILL")
public class SkillJPA {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private SkillPK id;

    @ManyToOne
    @MapsId("technologyId")
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    private TechnologyJPA technology;

    @ManyToOne
    @MapsId("developerId")
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private DeveloperJPA developer;

    @Nonnull
    private LocalDate startDate;
}
