package org.teamaker.developer.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.developer.adapter.out.persistence.repository.DeveloperRepository;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillCommand;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillPort;
import org.teamaker.developer.application.port.out.acquireSkill.AcquireSkillResponse;
import org.teamaker.technology.adapter.out.entity.SkillJPA;
import org.teamaker.technology.adapter.out.entity.SkillPK;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;
import org.teamaker.technology.adapter.out.persistence.repository.SkillRepository;
import org.teamaker.technology.adapter.out.persistence.repository.TechnologyRepository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Component
public class AcquireSkillAdapter implements AcquireSkillPort {
    private final DeveloperRepository developerRepository;
    private final TechnologyRepository technologyRepository;
    private final SkillRepository skillRepository;

    public AcquireSkillAdapter(DeveloperRepository developerRepository, TechnologyRepository technologyRepository, SkillRepository skillRepository) {
        this.developerRepository = developerRepository;
        this.technologyRepository = technologyRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public AcquireSkillResponse acquireSkill(AcquireSkillCommand command) throws NoSuchElementException, IllegalArgumentException {
        DeveloperJPA developer = developerRepository
                .findById(command.getDeveloperId())
                .orElseThrow(()
                        -> new NoSuchElementException("Developer not found with id : " + command.getDeveloperId()));

        if (developer.getResignationDate() != null)
            throw new IllegalArgumentException("Developer has resigned");

        TechnologyJPA technology = technologyRepository
                .findById(command.getTechnologyId())
                .orElseThrow(()
                        -> new NoSuchElementException("Technology not found with id : " + command.getTechnologyId()));

        SkillPK skillPK = new SkillPK(command.getDeveloperId(), command.getTechnologyId());
        LocalDate learningDate = LocalDate.now();
        skillRepository
                .findByDeveloperAndTechnology(developer, technology)
                .ifPresentOrElse(skillJPA -> {
                            throw new IllegalArgumentException("Developer already has the skill");
                        },
                        () -> skillRepository.save(new SkillJPA(skillPK, technology, developer, learningDate))
                );

        return new AcquireSkillResponse(technology.toDomain().toResponse(), learningDate);
    }
}
