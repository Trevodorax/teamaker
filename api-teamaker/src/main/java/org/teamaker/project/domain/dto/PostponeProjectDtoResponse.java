package org.teamaker.project.domain.dto;

import java.time.LocalDate;
import java.util.Objects;

public record PostponeProjectDtoResponse(String projectId, LocalDate newStartDate, LocalDate newEndDate) {
        public PostponeProjectDtoResponse {
            Objects.requireNonNull(projectId);
            Objects.requireNonNull(newStartDate);
        }
}
