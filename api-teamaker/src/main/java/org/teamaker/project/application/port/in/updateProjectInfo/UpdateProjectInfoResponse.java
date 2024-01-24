package org.teamaker.project.application.port.in.updateProjectInfo;

import org.teamaker.project.domain.dto.ProjectResponse;

public class UpdateProjectInfoResponse {
    public interface Response {}

    public record SuccessResponse(ProjectResponse project) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
