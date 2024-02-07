package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.util.UUID;

@Component
public class CreateDeveloperAdapter implements CreateDeveloperPort {
    private final DeveloperRepository developerRepository;

    public CreateDeveloperAdapter(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer createDeveloper(CreateDeveloperCommand command) throws IllegalArgumentException {
        String id = UUID.randomUUID().toString();
        while (developerRepository.existsById(id)) {
            id = UUID.randomUUID().toString();
        }

        DeveloperJPA developerJPA = new DeveloperJPA();
        developerJPA.setId(id);
        developerJPA.setName(command.getFullName());
        developerJPA.setEmail(command.getEmail());
        developerJPA.setHiringDate(command.getHiringDate());

        if (developerRepository.findByEmail(command.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        return developerRepository.save(developerJPA).toDomain();
    }
}
