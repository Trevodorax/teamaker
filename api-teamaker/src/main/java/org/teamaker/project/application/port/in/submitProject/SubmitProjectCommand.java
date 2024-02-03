package org.teamaker.project.application.port.in.submitProject;

import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class SubmitProjectCommand extends SelfValidating<SubmitProjectCommand> {
    @NotNull
    private final String name;
    private final String description;
    @NotNull
    private final ProjectPriority priority;
    @Future
    private final LocalDate startDate;
    private final LocalDate endDate;
//    private final ArrayList<UUID> technologies;
    private final Map<UUID, Integer> technologies;

    public SubmitProjectCommand(String name, String description, ProjectPriority priority, LocalDate startDate, LocalDate endDate, Map<UUID, Integer> technologies) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;

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

    public Map<UUID, Integer> getTechnologies() {
        return technologies;
    }
}
