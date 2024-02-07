package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.util.NoSuchElementException;

@Component
public class SaveDeveloperAdapter implements SaveDeveloperPort {
    private final DeveloperRepository developerRepository;

    public SaveDeveloperAdapter(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer saveDeveloper(SaveDeveloperCommand command) throws NoSuchElementException {
        DeveloperJPA developerJPA = developerRepository.findById(command.getDeveloper().getDeveloperId()).orElseThrow(NoSuchElementException::new);
        developerJPA.setName(command.getDeveloper().getFullName());
        developerJPA.setEmail(command.getDeveloper().getEmail());
        return developerRepository.save(developerJPA).toDomain();
    }
}
