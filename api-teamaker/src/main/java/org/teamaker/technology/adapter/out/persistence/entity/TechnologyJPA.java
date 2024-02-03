package org.teamaker.technology.adapter.out.persistence.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.technology.domain.Technology;

@Entity
@Data
@NoArgsConstructor
public class TechnologyJPA {
    @Id
    private String technologyId;
    @Nonnull
    private String name;

    public TechnologyJPA fromTechnology(Technology technology) {
        this.name = technology.getName();
        return this;
    }

    public Technology toTechnology() {
        return new Technology(this.technologyId, this.name);
    }
}
