package org.teamaker.project.application;

import org.teamaker.project.application.port.in.getNextProject.GetNextProject;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;
import org.teamaker.project.domain.Project;

public class GetNextProjectService implements GetNextProject {
    private final FindNextProjectPort findNextProject;

    public GetNextProjectService(FindNextProjectPort findNextProject) {
        this.findNextProject = findNextProject;
    }

    @Override
    public Project getNextProject() {
        return findNextProject.findNextProject();
    }
}
