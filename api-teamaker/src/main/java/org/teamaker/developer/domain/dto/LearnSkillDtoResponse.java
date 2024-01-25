package org.teamaker.developer.domain.dto;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.time.LocalDate;
import java.util.Objects;

public record LearnSkillDtoResponse(TechnologyResponse technology, LocalDate learningDate) {
    public LearnSkillDtoResponse {
        Objects.requireNonNull(technology);
        Objects.requireNonNull(learningDate);
    }
}
