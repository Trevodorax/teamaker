package org.teamaker.technology.application.port.in.getTechnology;

import org.teamaker.technology.domain.dto.TechnologyResponse;

public class GetTechnologyResponse {
    public interface Response {}

    public record SuccessResponse(TechnologyResponse technology) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
