package org.teamaker.project.application;

import org.teamaker.project.application.port.in.getNextProject.GetNextProject;
import org.teamaker.project.application.port.out.findNextProject.FindNextProject;
import org.teamaker.project.domain.Project;

public class GetNextProjectService implements GetNextProject {
    private final FindNextProject findNextProject;

    public GetNextProjectService(FindNextProject findNextProject) {
        this.findNextProject = findNextProject;
    }

    @Override
    public Project getNextProject() {
        return findNextProject.findNextProject();
    }
}
