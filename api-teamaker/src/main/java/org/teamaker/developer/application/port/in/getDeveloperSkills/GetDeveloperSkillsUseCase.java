package org.teamaker.developer.application.port.in.getDeveloperSkills;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.util.List;

public interface GetDeveloperSkillsUseCase {
    List<TechnologyResponse> getDeveloperSkills(String developerId);
}
