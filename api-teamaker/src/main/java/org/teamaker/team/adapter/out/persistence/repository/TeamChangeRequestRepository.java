package org.teamaker.team.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;

import java.util.List;
import java.util.Optional;

public interface TeamChangeRequestRepository extends JpaRepository<TeamChangeRequestJPA, String> {
    List<TeamChangeRequestJPA> findAllByDeveloperId(String developerId);

    boolean existsByRequestId(String requestId);

    Optional<TeamChangeRequestJPA> findByRequestId(String requestId);
}
