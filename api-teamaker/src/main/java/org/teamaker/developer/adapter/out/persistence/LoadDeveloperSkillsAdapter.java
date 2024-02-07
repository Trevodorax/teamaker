package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.technology.adapter.out.entity.SkillJPA;
import org.teamaker.technology.adapter.out.persistence.repository.SkillRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class LoadDeveloperSkillsAdapter implements LoadDeveloperSkillsPort {
    private final DeveloperRepository developerRepository;
    private final SkillRepository skillRepository;
    public LoadDeveloperSkillsAdapter(DeveloperRepository developerRepository, SkillRepository skillRepository) {
        this.developerRepository = developerRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<LoadDeveloperSkillsResponse> loadDeveloperSkills(LoadDeveloperSkillsCommand command) throws NoSuchElementException {
        DeveloperJPA developer = developerRepository
                .findById(command.getDeveloperId())
                .orElseThrow(()
                        -> new NoSuchElementException("Developer not found with id : " + command.getDeveloperId()));

        Set<SkillJPA> skills = skillRepository.findByDeveloper(developer);

        return skills.stream()
                .map(skill ->
                        new LoadDeveloperSkillsResponse(skill.getTechnology().toDomain().toResponse(), skill.getStartDate()))
                .toList();
    }
}
