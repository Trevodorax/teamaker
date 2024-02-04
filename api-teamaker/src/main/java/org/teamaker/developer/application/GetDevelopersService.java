package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.getDevelopers.GetDevelopersUseCase;
import org.teamaker.developer.application.port.out.loadDevelopers.LoadDevelopersPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

@Component
class GetDevelopersService implements GetDevelopersUseCase {
    private final LoadDevelopersPort loadDevelopersPort;

    public GetDevelopersService(LoadDevelopersPort loadDevelopersPort) {
        this.loadDevelopersPort = loadDevelopersPort;
    }

    @Override
    public List<DeveloperResponse> getDevelopers() {
        return loadDevelopersPort.loadDevelopers().stream().map(Developer::toResponse).toList();
    }
}
