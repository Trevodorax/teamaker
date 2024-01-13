package org.teamaker.developer.application.port.out.createDeveloper;

import org.teamaker.developer.domain.Developer;

public interface CreateDeveloperPort {
    Developer createDeveloper(CreateDeveloperCommand command);
}
