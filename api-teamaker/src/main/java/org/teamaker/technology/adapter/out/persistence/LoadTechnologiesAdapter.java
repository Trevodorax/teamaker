package org.teamaker.technology.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;
import org.teamaker.technology.application.port.out.loadTechnologies.LoadTechnologiesPort;
import org.teamaker.technology.domain.Technology;

import java.util.List;

@Component

public class LoadTechnologiesAdapter implements LoadTechnologiesPort {
    private final TechnologyRepository technologyRepository;

    public LoadTechnologiesAdapter(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Technology> loadTechnologies() {
        return technologyRepository
                .findAll()
                .stream()
                .map(TechnologyJPA::toDomain)
                .toList();
    }
}
