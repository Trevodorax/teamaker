package org.teamaker.technology.adapter.out.persistence.repository;

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
import java.util.Optional;

@Component
public interface TechnologyRepository extends JpaRepository<TechnologyJPA, String>,
        LoadTechnologiesPort {

    @Override
    default List<Technology> loadTechnologies() {
        return null;
    }

    Optional<TechnologyJPA> findByName(String name);
}
