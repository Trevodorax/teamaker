package org.teamaker.project.application.port.in.submitProject;

import lombok.Getter;
import org.teamaker.project.domain.ProjectPriority;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

@Getter
public class SubmitProjectCommand extends SelfValidating<SubmitProjectCommand> {
    @NotNull
    private final String name;
    private final String description;
    @NotNull
    private final ProjectPriority priority;
    @Future
    private final LocalDate startDate;
    private final LocalDate endDate;
    @NotNull
    private final Map<String, Integer> technologies; // format json :

    public SubmitProjectCommand(String name, String description, ProjectPriority priority, LocalDate startDate, LocalDate endDate, Map<String, Integer> technologies) {
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

}
