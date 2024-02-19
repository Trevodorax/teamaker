package org.teamaker.team.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipJPA;
import org.teamaker.team.adapter.out.entity.TeamMembershipPK;

import java.util.List;
import java.util.Set;

public interface TeamMembershipRepository extends JpaRepository<TeamMembershipJPA, TeamMembershipPK> {
    List<TeamMembershipJPA> findByDeveloper(DeveloperJPA developer);

    Set<TeamMembershipJPA> findAllByProject(ProjectJPA projectJPA);

    Set<TeamMembershipJPA> findByProjectId(String projectId);

    boolean existsByDeveloperAndProject(DeveloperJPA developerJPA, ProjectJPA requestedProjectJPA);
}
