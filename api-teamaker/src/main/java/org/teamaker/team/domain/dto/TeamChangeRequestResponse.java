package org.teamaker.team.domain.dto;

import org.teamaker.team.domain.TeamRequestStatus;

import java.time.LocalDate;
import java.util.Objects;

public record TeamChangeRequestResponse(String requestId, String developerId, String fromProjectId, String toProjectId, TeamRequestStatus status, LocalDate submitDate) {
    public TeamChangeRequestResponse {
        Objects.requireNonNull(requestId, "requestId must not be null");
        Objects.requireNonNull(developerId, "developerId must not be null");
        Objects.requireNonNull(fromProjectId, "fromProjectId must not be null");
        Objects.requireNonNull(toProjectId, "toProjectId must not be null");
        Objects.requireNonNull(status, "status must not be null");
        Objects.requireNonNull(submitDate, "submitDate must not be null");
    }
}
