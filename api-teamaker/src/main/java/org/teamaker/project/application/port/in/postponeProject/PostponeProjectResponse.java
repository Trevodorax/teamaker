package org.teamaker.project.application.port.in.postponeProject;

import org.teamaker.project.domain.dto.PostponeProjectDtoResponse;

public class PostponeProjectResponse {
    public interface Response {}

    public record SuccessResponse(PostponeProjectDtoResponse project) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
