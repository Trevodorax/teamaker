package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.loadDevelopers.LoadDevelopersPort;
import org.teamaker.developer.domain.Developer;

import java.util.List;

@Component
public class LoadDevelopersAdapter implements LoadDevelopersPort {
    private final DeveloperRepository developerRepository;

    public LoadDevelopersAdapter(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public List<Developer> loadDevelopers() {
        return developerRepository.findAll().stream().map(DeveloperJPA::toDomain).toList();
    }
}
