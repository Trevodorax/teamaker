package org.teamaker.technology.application.port.out;

import java.util.List;

import org.teamaker.technology.domain.Technology;

public interface LoadTechnologiesPort {
    public List<Technology> loadTechnologies();
}
