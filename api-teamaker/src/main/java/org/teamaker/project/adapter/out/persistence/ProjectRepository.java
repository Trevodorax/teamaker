package org.teamaker.project.adapter.out.persistence;

import org.teamaker.project.application.port.out.createProject.CreateProjectPort;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.application.port.out.loadProjects.LoadProjectsPort;
import org.teamaker.project.application.port.out.saveProject.SaveProjectPort;

public interface ProjectRepository extends CreateProjectPort, FindNextProjectPort, LoadProjectPort, LoadProjectsPort, SaveProjectPort {

}
