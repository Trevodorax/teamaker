package org.teamaker.project.application.port.in.getProjects;

import org.teamaker.project.domain.dto.ProjectResponse;

import java.util.List;

public class GetProjectsResponse {
    public interface Response {}

    public record SuccessResponse(List<ProjectResponse> projects) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
