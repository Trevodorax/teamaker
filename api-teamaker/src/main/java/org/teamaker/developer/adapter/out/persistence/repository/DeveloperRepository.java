package org.teamaker.developer.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<DeveloperJPA, String> {
    @Query("SELECT d FROM DeveloperJPA d JOIN d.skills s WHERE s.technology.id = :technologyId")
    List<DeveloperJPA> findDevelopersByTechnologyId(@Param("technologyId") String technologyId);

    Optional<DeveloperJPA> findByEmail(String email);

    @Query("SELECT d FROM DeveloperJPA d " +
            "JOIN d.skills s " +
            "WHERE NOT EXISTS (" +
            "  SELECT tm FROM TeamMembershipJPA tm " +
            "  WHERE tm.developer = d AND " +
            "  (tm.project.startDate < :newProjectEndDate OR :newProjectEndDate IS NULL) AND " +
            "  (tm.project.endDate > :newProjectStartDate OR tm.project.endDate IS NULL)) " +
            "AND EXISTS (" +
            "  SELECT tr FROM TechnologyRequirementJPA tr " +
            "  WHERE tr.project.id = :projectId AND tr.technology = s.technology) " +
            "GROUP BY d " +
            "HAVING COUNT(s) >= 1")
    List<DeveloperJPA> findAvailableDevelopersWithRequiredTechnologies(LocalDate newProjectStartDate, LocalDate newProjectEndDate, String projectId);
}
