package org.teamaker.developer.adapter.out.persistence;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.project.adapter.out.persistence.ProjectJPA;
import org.teamaker.technology.adapter.out.persistence.TechnologyJPA;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class DeveloperJPA {
    @Id
    private String developerId;
    @Nonnull
    private String fullName;
    @Nonnull
    private String email;
    @Nonnull
    private LocalDate hiringDate;
    private LocalDate resignationDate;
    @ManyToMany(mappedBy = "developers")
    private List<ProjectJPA> projectList;
    @ManyToMany(mappedBy = "developers")
    private List<TechnologyJPA> skills;
}
