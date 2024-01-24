package org.teamaker.technology.application.port.in.getTechnologies;

import java.util.List;

import org.teamaker.technology.domain.Technology;

public interface GetTechnologiesUseCase {
    public List<Technology> getTechnologies();
}
