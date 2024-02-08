package org.teamaker.technology.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyCommand;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyPort;
import org.teamaker.technology.domain.Technology;

import java.util.NoSuchElementException;
@Component

public class LoadTechnologyAdapter implements LoadTechnologyPort {
    private final TechnologyRepository technologyRepository;

    public LoadTechnologyAdapter(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public Technology loadTechnology(LoadTechnologyCommand command) throws NoSuchElementException {
        return technologyRepository
                .findById(command.getTechnologyId())
                .map(TechnologyJPA::toDomain)
                .orElseThrow(NoSuchElementException::new);
    }
}
