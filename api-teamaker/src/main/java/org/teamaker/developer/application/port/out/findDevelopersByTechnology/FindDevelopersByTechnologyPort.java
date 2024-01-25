package org.teamaker.developer.application.port.out.findDevelopersByTechnology;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public interface FindDevelopersByTechnologyPort {
    /**
     * Find developers by technology
     * @param command technology id
     * @return list of developers that use the technology or empty list if no developer uses the technology
     */
    List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command);
}
