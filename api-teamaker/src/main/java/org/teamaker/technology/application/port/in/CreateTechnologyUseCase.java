package org.teamaker.technology.application.port.in;

import org.teamaker.technology.domain.Technology;

public interface CreateTechnologyUseCase {
    public Technology createTechnology(CreateTechnologyCommand command);
}
