package org.teamaker.project.application.port.in.postponeProject;

import lombok.Getter;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class PostponeProjectRequest extends SelfValidating<PostponeProjectRequest> {
    @NotNull
    private final LocalDate newStartDate;
    private final LocalDate newEndDate;

    public PostponeProjectRequest(LocalDate newStartDate, LocalDate newEndDate) {
        this.newStartDate = newStartDate;
        this.newEndDate = newEndDate;
        this.validateSelf();

        if (newEndDate != null && newStartDate.isAfter(newEndDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }
    }
}
