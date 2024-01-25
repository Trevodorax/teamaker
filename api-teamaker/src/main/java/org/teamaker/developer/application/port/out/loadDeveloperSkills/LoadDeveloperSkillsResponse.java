package org.teamaker.developer.application.port.out.loadDeveloperSkills;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.time.LocalDate;
import java.util.Objects;

public record LoadDeveloperSkillsResponse(TechnologyResponse technology, LocalDate learningDate) {
    public LoadDeveloperSkillsResponse {
        Objects.requireNonNull(technology);
        Objects.requireNonNull(learningDate);
    }
}
