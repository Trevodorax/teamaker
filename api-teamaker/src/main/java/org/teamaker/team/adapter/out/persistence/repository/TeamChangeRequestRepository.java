package org.teamaker.team.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.team.adapter.out.entity.TeamChangeRequestJPA;

public interface TeamChangeRequestRepository extends JpaRepository<TeamChangeRequestJPA, String> {
}
