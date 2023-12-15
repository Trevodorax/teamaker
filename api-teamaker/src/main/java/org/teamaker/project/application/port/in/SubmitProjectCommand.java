package org.teamaker.project.application.port.in;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.teamaker.project.domain.Priority;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

class SubmitProjectCommand extends SelfValidating<SubmitProjectCommand> {
    @NotNull
    private final String name;
    private final String description;
    @NotNull
    private final Priority priority;
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

        this.validateSelf();
    }
}
