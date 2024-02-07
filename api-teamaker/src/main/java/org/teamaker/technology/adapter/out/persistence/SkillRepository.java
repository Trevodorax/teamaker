package org.teamaker.technology.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.technology.adapter.out.entity.SkillJPA;
import org.teamaker.technology.adapter.out.entity.SkillPK;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillJPA, SkillPK> {
    Optional<SkillJPA> findByTechnologyId(String technologyId);
}
