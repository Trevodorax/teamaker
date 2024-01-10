package org.teamaker.project.application.port.out.createProject;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.shared.validation.SelfValidating;

public class CreateProjectCommand extends SelfValidating<CreateProjectCommand> {
    @NotNull
    private final String name;
    private final String description;
    @NotNull
    private final ProjectPriority priority;
    @Future
    private final LocalDate startDate;
    private final LocalDate endDate;

    public CreateProjectCommand(String name, String description, ProjectPriority priority, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;

        if (endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }

        this.validateSelf();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
