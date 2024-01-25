package org.teamaker.developer.application.port.in.getDeveloperSkills;

import org.teamaker.developer.domain.dto.SkillResponse;

import java.util.List;

public class GetDeveloperSkillsResponse {
    public interface Response{}

    public record SuccessResponse(List<SkillResponse> skills) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
