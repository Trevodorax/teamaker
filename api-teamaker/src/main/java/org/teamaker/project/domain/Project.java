package org.teamaker.project.domain;

import java.time.LocalDate;

public class Project {
    private final String projectId;
    private String name;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String projectId, String name, String description, Priority priority, Status status, LocalDate startDate, LocalDate endDate) {
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

    public Status getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
