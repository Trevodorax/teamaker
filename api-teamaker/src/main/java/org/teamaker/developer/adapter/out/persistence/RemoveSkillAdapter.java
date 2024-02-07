package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillCommand;
import org.teamaker.developer.application.port.out.removeSkill.RemoveSkillPort;
import org.teamaker.technology.adapter.out.entity.SkillJPA;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.adapter.out.persistence.repository.SkillRepository;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;

import java.util.NoSuchElementException;

@Component
public class RemoveSkillAdapter implements RemoveSkillPort {
    private final DeveloperRepository developerRepository;
    private final SkillRepository skillRepository;
    private final TechnologyRepository technologyRepository;

    public RemoveSkillAdapter(DeveloperRepository developerRepository, SkillRepository skillRepository, TechnologyRepository technologyRepository) {
        this.developerRepository = developerRepository;
        this.skillRepository = skillRepository;
        this.technologyRepository = technologyRepository;
    }

    @Transactional
    @Override
    public void removeSkill(RemoveSkillCommand command) throws NoSuchElementException {
        DeveloperJPA developer = developerRepository
                .findById(command.getDeveloperId())
                .orElseThrow(()
                        -> new NoSuchElementException("Developer not found with id : " + command.getDeveloperId()));

        TechnologyJPA technology = technologyRepository
                .findById(command.getTechnologyId())
                .orElseThrow(()
                        -> new NoSuchElementException("Technology not found with id : " + command.getTechnologyId()));

        SkillJPA skill = skillRepository
                .findByDeveloperAndTechnology(developer, technology)
                .orElseThrow(()
                        -> new NoSuchElementException("Skill not found with developer id : " + command.getDeveloperId() + " and technology id : " + command.getTechnologyId()));

        skillRepository.delete(skill);
    }
}
