package org.teamaker.team.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipPK;

import java.util.List;

public interface TeamMembershipRepository extends JpaRepository<TeamMembershipJPA, TeamMembershipPK> {
    List<TeamMembershipJPA> findByDeveloper(DeveloperJPA developer);
}
