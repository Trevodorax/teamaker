package org.teamaker.project.domain;

import java.time.LocalDate;

public class Project {
    private final String projectId;
    private String name;
    private String description;
    private Priority priority;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String projectId, String name, String description, Priority priority, ProjectStatus status, LocalDate startDate, LocalDate endDate) {
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

    public Priority getPriority() {
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
        this.endDate = startDate.plusDays(daysToPostpone);
    }
}
