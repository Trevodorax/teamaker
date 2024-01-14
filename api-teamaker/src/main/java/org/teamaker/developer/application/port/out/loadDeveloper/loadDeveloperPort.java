package org.teamaker.developer.application.port.out.loadDeveloper;

import org.teamaker.developer.domain.Developer;

public interface loadDeveloperPort {
    Developer loadDeveloper(loadDeveloperCommand command);
}
