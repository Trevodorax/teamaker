package org.teamaker.technology.application.port.out.createTechnology;

import org.teamaker.technology.domain.Technology;

public interface CreateTechnologyPort {
    Technology createTechnology(CreateTechnologyCommand command);
}
