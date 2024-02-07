package org.teamaker.project.domain;

import lombok.Getter;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.dto.ProjectResponse;
import org.teamaker.team.domain.Team;
import org.teamaker.technology.domain.Technology;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
public class Project {
    private final String projectId;
    private String name;
    private String description;
    private ProjectPriority priority;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Team team;
    private Map<Technology, Integer> technologies;

    public Project(String projectId, String name, String description, ProjectPriority priority, ProjectStatus status, LocalDate startDate, LocalDate endDate, Team team, Map<Technology, Integer> technologies) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.team = team;
        this.technologies = technologies;
    }

    public List<String> removeDeveloperById(String developerId, boolean noRemove) throws IllegalStateException {
        return team.removeDeveloperById(developerId, this, noRemove);
    }

    public List<String> addDeveloper(Developer newDeveloper, boolean noAdd) throws IllegalStateException {
        return team.addDeveloper(newDeveloper, this, noAdd);
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

    /**
     * @param checkedProject Project for which we check if it is overlapping with this one
     * @return Are the projects overlapping?
     */
    public boolean isOverlapping(Project checkedProject) {
        return !(endDate.isBefore(checkedProject.startDate) || startDate.isAfter(checkedProject.endDate));
    }

    public Duration getDuration() {
        return Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay());
    }
}
