package org.teamaker.developer.application.port.out;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public interface FindDevelopersByTechnologyPort {
    public List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command);
}
