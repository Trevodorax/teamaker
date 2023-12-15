package org.teamaker.project.application.port.in;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.teamaker.project.domain.Priority;
import org.teamaker.shared.validation.SelfValidating;
import org.teamaker.shared.validation.date.IsAfter;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

class SubmitProjectCommand extends SelfValidating<SubmitProjectCommand> {
    @NotNull
    private final String name;
    private final String description;
    @NotNull
    private final Priority priority;
    @Future
    private final LocalDate startDate;
    private final LocalDate endDate;
    @NotNull
    private final ArrayList<UUID> technologies;

    public SubmitProjectCommand(String name, String description, Priority priority, LocalDate startDate, LocalDate endDate, ArrayList<UUID> technologies) {
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

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<UUID> getTechnologies() {
        return technologies;
    }
}
