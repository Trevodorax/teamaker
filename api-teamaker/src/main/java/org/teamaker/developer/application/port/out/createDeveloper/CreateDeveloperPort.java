package org.teamaker.developer.application.port.out.createDeveloper;

import org.teamaker.developer.domain.Developer;

public interface CreateDeveloperPort {
    /**
     * Creates a developer in persistence
     * @param command the information about the developer
     * @return the created developer
     * @throws IllegalArgumentException If email is already taken
     */
    Developer createDeveloper(CreateDeveloperCommand command) throws IllegalArgumentException;
}
