package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.project.application.port.in.getNextProject.GetNextProject;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;

@Component
public class GetNextProjectService implements GetNextProject {
    private final FindNextProjectPort findNextProject;

    public GetNextProjectService(FindNextProjectPort findNextProject) {
        this.findNextProject = findNextProject;
    }

    @Override
    public ProjectResponse getNextProject() {
        return findNextProject.findNextProject().toResponse();
    }
}
