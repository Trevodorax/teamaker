package org.teamaker.technology.application.port.in.getTechnologies;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.util.List;

public interface GetTechnologiesUseCase {
    List<TechnologyResponse> getTechnologies();
}
