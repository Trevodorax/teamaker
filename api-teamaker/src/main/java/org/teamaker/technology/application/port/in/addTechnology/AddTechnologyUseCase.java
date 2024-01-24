package org.teamaker.technology.application.port.in.addTechnology;

import org.teamaker.technology.domain.Technology;
import org.teamaker.technology.domain.dto.TechnologyResponse;

public interface AddTechnologyUseCase {
    AddTechnologyResponse.Response addTechnology(AddTechnologyCommand command);
}
