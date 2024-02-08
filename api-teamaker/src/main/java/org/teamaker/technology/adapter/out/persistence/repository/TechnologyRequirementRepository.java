package org.teamaker.technology.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.technology.adapter.out.entity.TechnologyRequirementJPA;
import org.teamaker.technology.adapter.out.entity.TechnologyRequirementPK;

public interface TechnologyRequirementRepository extends JpaRepository<TechnologyRequirementJPA, TechnologyRequirementPK> {
}
