package org.teamaker.developer.application.port.out.saveDeveloper;

import org.teamaker.developer.domain.Developer;

import java.util.NoSuchElementException;

public interface SaveDeveloperPort {
    /**
     * Save a developer
     * @param command the command to save a developer
     * @return the saved developer
     * @throws NoSuchElementException if the developer is not found
     */
    Developer saveDeveloper(SaveDeveloperCommand command) throws NoSuchElementException;
}
