package org.teamaker.technology.application.port.out.createTechnology;

import org.teamaker.technology.domain.Technology;

public interface CreateTechnologyPort {
    public Technology createTechnology(CreateTechnologyCommand command);
}
