package org.teamaker.project.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.teamaker.project.adapter.out.persistence.repository.ProjectRepository;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class FindNextProjectAdapter implements FindNextProjectPort {
    private final ProjectRepository projectRepository;

    public FindNextProjectAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findNextProject() throws NoSuchElementException {
        List<ProjectStatus> statuses = List.of(ProjectStatus.PENDING, ProjectStatus.ACCEPTED);
        return projectRepository
                .findFirstByStatusInAndStartDateAfterOrderByStartDateAsc(statuses, LocalDate.now())
                .orElseThrow(() -> new NoSuchElementException("No project found"))
                .toDomain();
    }
}
