package org.teamaker.technology.application;

import org.springframework.stereotype.Component;
import org.teamaker.technology.application.port.in.getTechnologies.GetTechnologiesUseCase;
import org.teamaker.technology.application.port.out.loadTechnologies.LoadTechnologiesPort;
import org.teamaker.technology.domain.Technology;
import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.util.List;

@Component
class GetTechnologiesService implements GetTechnologiesUseCase{
    private final LoadTechnologiesPort loadTechnologiesPort;

    public GetTechnologiesService(LoadTechnologiesPort loadTechnologiesPort) {
        this.loadTechnologiesPort = loadTechnologiesPort;
    }

    @Override
    public List<TechnologyResponse> getTechnologies() {
        return loadTechnologiesPort.loadTechnologies()
                .stream()
                .map(Technology::toResponse)
                .toList();
    }
}
