package org.teamaker.technology.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.project.adapter.out.persistence.ProjectJPA;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyRequirementJPA {
    @EmbeddedId
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
