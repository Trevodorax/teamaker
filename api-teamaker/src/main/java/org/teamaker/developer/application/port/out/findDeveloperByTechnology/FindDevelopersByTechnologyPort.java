package org.teamaker.developer.application.port.out.findDeveloperByTechnology;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public interface FindDevelopersByTechnologyPort {
    List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command);
}
