package org.teamaker.developer.application.port.in.getDevelopers;

import org.teamaker.developer.application.dto.DeveloperResponse;

import java.util.List;

public interface GetDevelopersUseCase {
    List<DeveloperResponse> getDevelopers();
}
