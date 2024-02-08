package org.teamaker.project.application.port.in.postponeProject;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class PostponeProjectCommand extends SelfValidating<PostponeProjectCommand> {
    @NotNull
    private final String projectId;

    @Future
    @NotNull
    private final LocalDate newStartDate;

    @Future
    private final LocalDate newEndDate;

    public PostponeProjectCommand(String projectId, LocalDate newStartDate, LocalDate newEndDate) {
        this.projectId = projectId;
        this.newStartDate = newStartDate;
        this.newEndDate = newEndDate;

        this.validateSelf();

        if (newEndDate != null && newStartDate.isAfter(newEndDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }
    }

}
