package org.teamaker.technology.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.project.adapter.out.entity.ProjectJPA;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TECHNOLOGY_REQUIREMENT")
public class TechnologyRequirementJPA {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private TechnologyRequirementPK technologyRequirementPK;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectJPA project;

    @ManyToOne
    @MapsId("technologyId")
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    private TechnologyJPA technology;

    @Nonnull
    private Integer devsNeeded;
}
