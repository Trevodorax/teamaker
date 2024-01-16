package org.teamaker.project.domain;

import org.teamaker.project.domain.dto.ProjectResponse;

import java.time.LocalDate;

public class Project {
    private final String projectId;
    private String name;
    private String description;
    private ProjectPriority priority;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String projectId, String name, String description, ProjectPriority priority, ProjectStatus status, LocalDate startDate, LocalDate endDate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectPriority getPriority() {
        return priority;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void postpone(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void postpone(LocalDate startDate) {
        int daysToPostpone = startDate.getDayOfMonth() - this.startDate.getDayOfMonth();
        this.startDate = startDate;
        this.endDate = endDate.plusDays(daysToPostpone);
    }

    public void treat(ProjectStatus status) {
        this.status = status;
    }

    public ProjectProgress projectProgress() {
        if (this.status == ProjectStatus.PENDING) {
            return ProjectProgress.NOT_STARTED;
        } else if (this.status == ProjectStatus.ACCEPTED) {
            LocalDate today = LocalDate.now();
            if (today.isBefore(this.startDate)) {
                return ProjectProgress.NOT_STARTED;
            } else if (today.isAfter(this.endDate)) {
                return ProjectProgress.DONE;
            } else {
                return ProjectProgress.IN_PROGRESS;
            }
        } else {
            return ProjectProgress.ABORTED;
        }
    }

    public void updateInfo(String newName, String newDescription, ProjectPriority newPriority) {
        this.name = newName != null ? newName : this.name;
        this.description = newDescription != null ? newDescription : this.description;
        this.priority = newPriority != null ? newPriority : this.priority;
    }

    public ProjectResponse toResponse() {
        return new ProjectResponse(this.projectId, this.name, this.description, this.status, this.priority, this.startDate, this.endDate, this.projectProgress());
    }
}
