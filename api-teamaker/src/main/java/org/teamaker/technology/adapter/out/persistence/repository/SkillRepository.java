package org.teamaker.technology.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.technology.adapter.out.entity.SkillJPA;
import org.teamaker.technology.adapter.out.entity.SkillPK;
import org.teamaker.technology.adapter.out.entity.TechnologyJPA;

import java.util.Optional;
import java.util.Set;

public interface SkillRepository extends JpaRepository<SkillJPA, SkillPK> {
    Optional<SkillJPA> findByDeveloperAndTechnology(DeveloperJPA developer, TechnologyJPA technology);

    Set<SkillJPA> findByDeveloper(DeveloperJPA developer);

    void deleteByDeveloperAndTechnology(DeveloperJPA developer, TechnologyJPA technology);
}
