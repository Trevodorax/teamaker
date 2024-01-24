package org.teamaker.technology.application.port.in.getTechnologies;

import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.util.List;

public class GetTechnologiesResponse {
    public interface Response {}

    public record SuccessResponse(List<TechnologyResponse> technologies) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
