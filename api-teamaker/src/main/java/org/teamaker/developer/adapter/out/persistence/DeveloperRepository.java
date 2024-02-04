package org.teamaker.developer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillPort;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillResponse;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperCommand;
import org.teamaker.developer.application.port.out.createDeveloper.CreateDeveloperPort;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.out.findDevelopersByTechnology.FindDevelopersByTechnologyPort;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.application.port.out.loadDevelopers.LoadDevelopersPort;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillCommand;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;

import java.util.List;
import java.util.NoSuchElementException;

public interface DeveloperRepository extends JpaRepository<DeveloperJPA, String>,
        AcquireSkillPort,
        CreateDeveloperPort,
        FindDevelopersByTechnologyPort,
        LoadDeveloperPort,
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
        return null;
    }

    @Override
    default List<Developer> findDevelopersByTechnology(FindDevelopersByTechnologyCommand command) throws NoSuchElementException {
        return null;
    }

    @Override
    default Developer loadDeveloper(LoadDeveloperCommand command) throws NoSuchElementException {
        return null;
    }

    @Override
    default List<Developer> loadDevelopers() {
        return null;
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
