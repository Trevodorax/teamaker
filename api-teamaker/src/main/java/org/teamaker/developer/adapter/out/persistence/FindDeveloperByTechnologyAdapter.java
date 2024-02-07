package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.domain.Developer;

import java.util.List;

@Component
public class FindDeveloperByTechnologyAdapter implements FindDevelopersByTechnologyPort {
    private final DeveloperRepository developerRepository;

    public FindDeveloperByTechnologyAdapter(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command) {
        return developerRepository
                .findDevelopersByTechnologyId(command.getTechnologyId())
                .stream()
                .map(DeveloperJPA::toDomain)
                .toList();
    }
}
