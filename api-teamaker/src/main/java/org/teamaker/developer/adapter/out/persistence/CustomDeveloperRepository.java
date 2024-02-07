package org.teamaker.developer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;

import java.util.List;
import java.util.Optional;

public interface CustomDeveloperRepository extends JpaRepository<DeveloperJPA, String> {
    Optional<DeveloperJPA> findByEmail(String email);

    @Query("SELECT d FROM DeveloperJPA d JOIN d.skills s WHERE s.technology.id = :technologyId AND d.resignationDate IS NULL")
    List<DeveloperJPA> findDevelopersByTechnologyId(@Param("technologyId") String technologyId);
}
