package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.util.NoSuchElementException;

@Component
public class LoadDeveloperAdapter implements LoadDeveloperPort {
    private final DeveloperRepository developerRepository;

    public LoadDeveloperAdapter(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer loadDeveloper(LoadDeveloperCommand command) throws NoSuchElementException {
        return developerRepository
                .findById(command.getDeveloperId())
                .map(DeveloperJPA::toDomain)
                .orElseThrow(NoSuchElementException::new);
    }
}
