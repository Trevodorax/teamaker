package org.teamaker.developer.application.port.out.findDevelopersByTechnology;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public interface FindDevelopersByTechnologyPort {
    List<DeveloperResponse> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command);
}
