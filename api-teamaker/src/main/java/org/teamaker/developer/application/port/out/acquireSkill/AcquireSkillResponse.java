package org.teamaker.developer.application.port.out.acquireSkill;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.time.LocalDate;
import java.util.Objects;

public record AcquireSkillResponse(TechnologyResponse technology, LocalDate learningDate) {
    public AcquireSkillResponse {
        Objects.requireNonNull(technology);
        Objects.requireNonNull(learningDate);
    }
}
