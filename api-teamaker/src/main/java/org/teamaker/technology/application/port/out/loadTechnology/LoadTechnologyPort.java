package org.teamaker.technology.application.port.out.loadTechnology;

import org.teamaker.technology.domain.Technology;

import java.util.NoSuchElementException;

public interface LoadTechnologyPort {
    /**
     * Load a technology by its id
     * @param command the command to load the technology
     * @return the technology
     * @throws NoSuchElementException if the technology does not exist
     */
    Technology loadTechnology(LoadTechnologyCommand command) throws NoSuchElementException;
}
