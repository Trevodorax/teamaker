package org.teamaker.developer.application.port.out.saveDeveloper;

import org.teamaker.developer.domain.Developer;

public interface SaveDeveloperPort {
    Developer saveDeveloper(SaveDeveloperCommand command);
}
