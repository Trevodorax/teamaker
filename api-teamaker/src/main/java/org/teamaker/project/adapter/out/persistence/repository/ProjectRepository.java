package org.teamaker.project.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.entity.ProjectJPA;
import org.teamaker.project.application.port.out.createProject.CreateProjectCommand;
import org.teamaker.project.application.port.out.createProject.CreateProjectPort;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectCommand;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;
import org.teamaker.project.domain.Project;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public interface ProjectRepository extends JpaRepository<ProjectJPA, String> {
}
