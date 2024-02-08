package org.teamaker.project.domain.dto;

import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.project.domain.ProjectProgress;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;
import java.util.Objects;

public record ProjectResponse(String projectId, String name, String description, ProjectStatus status, ProjectPriority priority, LocalDate startDate, LocalDate endDate, ProjectProgress progress) {
    public ProjectResponse {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(status);
        Objects.requireNonNull(priority);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(progress);
    }
}
