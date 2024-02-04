package org.teamaker.developer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillPort;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillResponse;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.application.port.out.loadDevelopers.LoadDevelopersPort;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillCommand;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Component
public interface DeveloperRepository extends JpaRepository<DeveloperJPA, String>,
        CustomDeveloperRepository,
        AcquireSkillPort,
        CreateDeveloperPort,
        FindDevelopersByTechnologyPort,
        LoadDeveloperPort,
        LoadDeveloperProjectsPort,
        LoadDevelopersPort,
        LoadDeveloperSkillsPort,
        RemoveSkillPort,
        SaveDeveloperPort {
    @Override
    default AcquireSkillResponse acquireSkill(AcquireSkillCommand command) throws NoSuchElementException {
        return null;
    }

    @Override
    default Developer createDeveloper(CreateDeveloperCommand command) throws IllegalArgumentException {
        String id = UUID.randomUUID().toString();
        while (existsById(id)) {
            id = UUID.randomUUID().toString();
        }

        DeveloperJPA developerJPA = new DeveloperJPA();
        developerJPA.setId(id);
        developerJPA.setName(command.getFullName());
        developerJPA.setEmail(command.getEmail());
        developerJPA.setHiringDate(command.getHiringDate());

        if (findByEmail(command.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        return save(developerJPA).toDomain();
    }

    @Override
    default List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command) throws NoSuchElementException {
        return null;
    }

    @Override
    default Developer loadDeveloper(LoadDeveloperCommand command) throws NoSuchElementException {
        return findById(command.getDeveloperId()).map(DeveloperJPA::toDomain).orElseThrow(NoSuchElementException::new);
    }

    @Override
    default List<Project> loadDeveloperProjects(LoadDeveloperProjectsCommand command) throws NoSuchElementException {
        return null;
    }

    @Override
    default List<Developer> loadDevelopers() {
        return findAll().stream().map(DeveloperJPA::toDomain).toList();
    }

    @Override
    default List<LoadDeveloperSkillsResponse> loadDeveloperSkills(LoadDeveloperSkillsCommand command) throws NoSuchElementException {
        return null;
    }

    @Override
    default void removeSkill(RemoveSkillCommand command) throws NoSuchElementException {
    }

    @Override
    default Developer saveDeveloper(SaveDeveloperCommand command) {
        return null;
    }

}
