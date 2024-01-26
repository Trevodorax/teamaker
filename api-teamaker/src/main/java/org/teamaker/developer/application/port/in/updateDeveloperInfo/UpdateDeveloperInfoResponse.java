package org.teamaker.developer.application.port.in.updateDeveloperInfo;

import org.teamaker.developer.domain.dto.DeveloperResponse;

public class UpdateDeveloperInfoResponse {
    public interface Response {}

    public record SuccessResponse(DeveloperResponse developer) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
