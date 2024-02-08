package org.teamaker.technology.adapter.out.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamaker.technology.domain.Technology;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TECHNOLOGY")
public class TechnologyJPA {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Nonnull
    private String name;

    @OneToMany(mappedBy = "technology")
    private Set<TechnologyRequirementJPA> technologyRequirements;

    public Technology toDomain() {
        return new Technology(id, name);
    }
}
