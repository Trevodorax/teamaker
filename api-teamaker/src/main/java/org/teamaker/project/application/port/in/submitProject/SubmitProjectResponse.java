package org.teamaker.project.application.port.in.submitProject;

import org.teamaker.project.domain.dto.ProjectResponse;

public class SubmitProjectResponse {
    public interface Response {}

    public record SuccessResponse(ProjectResponse project) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
