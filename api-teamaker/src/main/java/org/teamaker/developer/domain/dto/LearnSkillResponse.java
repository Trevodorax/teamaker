package org.teamaker.developer.domain.dto;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.time.LocalDate;
import java.util.Objects;

public record LearnSkillResponse(TechnologyResponse technology, LocalDate learningDate) {
    public LearnSkillResponse {
        Objects.requireNonNull(technology);
        Objects.requireNonNull(learningDate);
    }
}
