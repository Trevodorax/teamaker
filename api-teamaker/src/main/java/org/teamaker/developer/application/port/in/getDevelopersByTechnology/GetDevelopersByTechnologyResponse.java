package org.teamaker.developer.application.port.in.getDevelopersByTechnology;

import org.teamaker.developer.domain.dto.GetDevelopersByTechnologyDtoResponse;

public class GetDevelopersByTechnologyResponse {
    public interface Response {}

    public record SuccessResponse(GetDevelopersByTechnologyDtoResponse developers) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
