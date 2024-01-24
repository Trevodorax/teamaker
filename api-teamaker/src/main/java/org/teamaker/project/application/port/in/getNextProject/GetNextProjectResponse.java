package org.teamaker.project.application.port.in.getNextProject;

import org.teamaker.project.domain.dto.ProjectResponse;

public class GetNextProjectResponse {
    public interface Response {}

    public record SuccessResponse(ProjectResponse project) implements GetNextProjectResponse.Response {}

    public record ErrorResponse(String errorMessage) implements GetNextProjectResponse.Response {}
}
