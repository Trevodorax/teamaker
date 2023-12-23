package org.teamaker.developer.application.port.out;

import org.teamaker.developer.domain.Developer;

public interface CreateDeveloperPort {
    public Developer createDeveloper(CreateDeveloperCommand command);
}
