package org.teamaker.technology.application;

import org.teamaker.technology.application.port.out.LoadTechnologiesPort;
import org.teamaker.technology.application.port.in.GetTechnologiesUseCase;
import org.teamaker.technology.domain.Technology;

import java.util.List;

public class GetTechnologiesService implements GetTechnologiesUseCase{
    private final LoadTechnologiesPort loadTechnologiesPort;

    public GetTechnologiesService(LoadTechnologiesPort loadTechnologiesPort) {
        this.loadTechnologiesPort = loadTechnologiesPort;
    }

    @Override
    public List<Technology> getTechnologies() {
        return loadTechnologiesPort.loadTechnologies();
    }
}
