package org.teamaker.technology.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyPort;
import org.teamaker.technology.application.port.out.loadTechnologies.LoadTechnologiesPort;
import org.teamaker.technology.domain.Technology;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<TechnologyJPA, String>, CreateTechnologyPort, LoadTechnologiesPort {
    @Override
    default Technology createTechnology(CreateTechnologyCommand command) {
        TechnologyJPA technologyJPA = new TechnologyJPA();
        technologyJPA.setName(command.getName());
        return save(technologyJPA).toTechnology();
    }

    @Override
    default List<Technology> loadTechnologies() {
        return findAll()
                .stream()
                .map(TechnologyJPA::toTechnology)
                .toList();
    }
}
