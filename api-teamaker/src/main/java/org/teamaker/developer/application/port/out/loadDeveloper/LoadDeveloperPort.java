package org.teamaker.developer.application.port.out.loadDeveloper;

import org.teamaker.developer.domain.Developer;

public interface LoadDeveloperPort {
    Developer loadDeveloper(LoadDeveloperCommand command);
}
