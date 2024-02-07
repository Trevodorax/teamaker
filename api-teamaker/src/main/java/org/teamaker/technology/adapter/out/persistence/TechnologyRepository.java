package org.teamaker.technology.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyCommand;
import org.teamaker.technology.application.port.out.createTechnology.CreateTechnologyPort;
import org.teamaker.technology.application.port.out.loadTechnologies.LoadTechnologiesPort;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyCommand;
import org.teamaker.technology.application.port.out.loadTechnology.LoadTechnologyPort;
import org.teamaker.technology.domain.Technology;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public interface TechnologyRepository extends JpaRepository<TechnologyJPA, String>,
        CreateTechnologyPort,
        LoadTechnologiesPort,
        LoadTechnologyPort {
//    @Override
//    default Technology createTechnology(CreateTechnologyCommand command) {
//        TechnologyJPA technologyJPA = new TechnologyJPA();
//        technologyJPA.setName(command.getName());
//        return save(technologyJPA).toTechnology();
//    }
//
//    @Override
//    default List<Technology> loadTechnologies() {
//        return findAll()
//                .stream()
//                .map(TechnologyJPA::toTechnology)
//                .toList();
//    }

    @Override
    default Technology createTechnology(CreateTechnologyCommand command) {
        return null;
    }

    @Override
    default List<Technology> loadTechnologies() {
        return null;
    }

    @Override
    default Technology loadTechnology(LoadTechnologyCommand command) {
        return findById(command.getTechnologyId())
                .map(TechnologyJPA::toDomain)
                .orElseThrow(NoSuchElementException::new);
    }
}
