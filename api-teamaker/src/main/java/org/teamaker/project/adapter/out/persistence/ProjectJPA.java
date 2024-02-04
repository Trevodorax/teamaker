package org.teamaker.project.adapter.out.persistence;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamaker.developer.adapter.out.persistence.DeveloperJPA;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.technology.adapter.out.persistence.TechnologyJPA;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ProjectJPA {
    @Id
    private String projectId;
    @Nonnull
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @Enumerated(EnumType.STRING)
    private ProjectPriority priority;
    @Nonnull
    private LocalDate startDate;
    @Nonnull
    private LocalDate endDate;
    @ManyToMany(mappedBy = "projectList")
    private List<DeveloperJPA> developers;
    @ManyToMany(mappedBy = "projectList")
    private List<TechnologyJPA> technologies;
    @Nonnull
    private Integer minDevelopers;
}
