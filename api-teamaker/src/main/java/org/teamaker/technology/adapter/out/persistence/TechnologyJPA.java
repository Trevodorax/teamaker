package org.teamaker.technology.adapter.out.persistence;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.persistence.DeveloperJPA;
import org.teamaker.project.adapter.out.persistence.ProjectJPA;
import org.teamaker.technology.domain.Technology;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TechnologyJPA {
    @Id
    private String technologyId;
    @Nonnull
    private String name;
    @ManyToMany(mappedBy = "skills")
    private List<DeveloperJPA> developers;
    @ManyToMany(mappedBy = "technologies")
    private List<ProjectJPA> projectList;

    public TechnologyJPA fromTechnology(Technology technology) {
        this.name = technology.getName();
        return this;
    }

    public Technology toTechnology() {
        return new Technology(this.technologyId, this.name);
    }
}
