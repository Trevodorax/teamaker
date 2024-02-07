package org.teamaker.developer.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.technology.adapter.out.entity.SkillJPA;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<DeveloperJPA, String> {
    @Query("SELECT d FROM DeveloperJPA d JOIN d.skills s WHERE s.technology.id = :technologyId AND d.resignationDate IS NULL")
    List<DeveloperJPA> findDevelopersByTechnologyId(@Param("technologyId") String technologyId);

    Optional<DeveloperJPA> findByEmail(String email);
}
