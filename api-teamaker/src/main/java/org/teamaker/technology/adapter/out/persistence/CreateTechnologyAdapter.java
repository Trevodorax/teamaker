package org.teamaker.technology.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyPort;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;
import org.teamaker.technology.domain.Technology;

import java.util.UUID;

@Component
public class CreateTechnologyAdapter implements CreateTechnologyPort {
    private final TechnologyRepository technologyRepository;

    public CreateTechnologyAdapter(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public Technology createTechnology(CreateTechnologyCommand command) throws IllegalArgumentException {
        String id = UUID.randomUUID().toString();
        while (technologyRepository.existsById(id)) {
            id = UUID.randomUUID().toString();
        }

        TechnologyJPA technologyJPA = new TechnologyJPA();
        technologyJPA.setId(id);
        technologyJPA.setName(command.getName());

        if (technologyRepository.findByName(command.getName()).isPresent()) {
            throw new IllegalArgumentException("Technology already exists");
        }

        return technologyRepository.save(technologyJPA).toDomain();
    }
}
