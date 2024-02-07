package org.teamaker.developer.application.port.in.getDeveloperProjects;

import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.dto.ProjectResponse;

import java.util.List;

public class GetDeveloperProjectsResponse {
    public interface Response {}

    public record SuccessResponse(List<ProjectResponse> projects) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
