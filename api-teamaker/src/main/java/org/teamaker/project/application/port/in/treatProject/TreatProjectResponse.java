package org.teamaker.project.application.port.in.treatProject;

import org.teamaker.project.domain.dto.TreatProjectDtoResponse;

public class TreatProjectResponse {
    public interface Response {}

    public record SuccessResponse(TreatProjectDtoResponse project) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
