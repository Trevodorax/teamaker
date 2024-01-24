package org.teamaker.technology.application.port.out.loadTechnologies;

import java.util.List;

import org.teamaker.technology.domain.Technology;

public interface LoadTechnologiesPort {
    /**
     * Load all technologies
     * @return List of technologies or empty list if no technologies found
     */
    List<Technology> loadTechnologies();
}
