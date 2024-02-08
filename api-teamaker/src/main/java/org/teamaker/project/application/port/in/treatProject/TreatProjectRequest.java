package org.teamaker.project.application.port.in.treatProject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.teamaker.project.domain.ProjectStatus;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class TreatProjectRequest extends SelfValidating<TreatProjectRequest> {
    @NotNull
    private final ProjectStatus status;

    @JsonCreator
    public TreatProjectRequest(@JsonProperty("status") ProjectStatus status) {
        this.status = status;
        this.validateSelf();
    }
}
