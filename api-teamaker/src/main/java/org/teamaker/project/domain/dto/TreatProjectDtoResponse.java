package org.teamaker.project.domain.dto;

import org.teamaker.project.domain.ProjectStatus;

import java.util.Objects;

public record TreatProjectDtoResponse(String projectId, ProjectStatus status) {
    public TreatProjectDtoResponse {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(status);
    }
}
