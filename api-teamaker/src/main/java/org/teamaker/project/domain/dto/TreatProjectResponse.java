package org.teamaker.project.domain.dto;

import org.teamaker.project.domain.ProjectStatus;

import java.util.Objects;

public record TreatProjectResponse(String projectId, ProjectStatus status) {
    public TreatProjectResponse {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(status);
    }
}
