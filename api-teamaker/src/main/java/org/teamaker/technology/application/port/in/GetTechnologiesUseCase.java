package org.teamaker.technology.application.port.in;

import org.teamaker.technology.domain.Technology;

import java.util.List;

public interface GetTechnologiesUseCase {
    public List<Technology> getTechnologies();
}
