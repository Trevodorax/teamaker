package org.teamaker.project.application.port.dto;

import java.util.Objects;

import org.teamaker.project.domain.ProjectStatus;

public record TreatProjectResponse(String projectId, ProjectStatus status) {
    public TreatProjectResponse {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(status);
    }
}
