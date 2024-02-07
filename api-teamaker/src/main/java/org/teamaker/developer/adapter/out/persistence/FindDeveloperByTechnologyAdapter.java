package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class FindDeveloperByTechnologyAdapter implements FindDevelopersByTechnologyPort {
    private final DeveloperRepository developerRepository;
    private final TechnologyRepository technologyRepository;

    public FindDeveloperByTechnologyAdapter(DeveloperRepository developerRepository, TechnologyRepository technologyRepository) {
        this.developerRepository = developerRepository;
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command) throws NoSuchElementException {
        if (!technologyRepository.existsById(command.getTechnologyId()))
            throw new NoSuchElementException("Technology not found with id : " + command.getTechnologyId());
        return developerRepository
                .findDevelopersByTechnologyId(command.getTechnologyId())
                .stream()
                .map(DeveloperJPA::toDomain)
                .toList();
    }
}
