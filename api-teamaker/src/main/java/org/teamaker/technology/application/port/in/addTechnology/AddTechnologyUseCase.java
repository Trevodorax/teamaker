package org.teamaker.technology.application.port.in.addTechnology;

import org.teamaker.technology.domain.Technology;

public interface AddTechnologyUseCase {
    public Technology addTechnology(AddTechnologyCommand command);
}
