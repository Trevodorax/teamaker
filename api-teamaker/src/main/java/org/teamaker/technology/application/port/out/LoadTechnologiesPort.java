package org.teamaker.technology.application.port.out;

import org.teamaker.technology.domain.Technology;

import java.util.List;

public interface LoadTechnologiesPort {
    public List<Technology> loadTechnologies();
}
