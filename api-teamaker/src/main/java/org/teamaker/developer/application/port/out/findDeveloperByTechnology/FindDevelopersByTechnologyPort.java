package org.teamaker.developer.application.port.out.findDeveloperByTechnology;

import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public interface FindDevelopersByTechnologyPort {
    List<DeveloperResponse> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command);
}
