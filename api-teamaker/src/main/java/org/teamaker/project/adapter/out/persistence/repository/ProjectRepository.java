package org.teamaker.project.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface ProjectRepository extends JpaRepository<ProjectJPA, String> {
    Optional<ProjectJPA> findFirstByStatusInAndStartDateAfterOrderByStartDateAsc(List<ProjectStatus> statuses, LocalDate startDate);

}
