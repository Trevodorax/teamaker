package org.teamaker.developer.application.port.in.learnSkill;

import org.teamaker.developer.domain.dto.LearnSkillDtoResponse;

import java.util.List;

public class LearnSkillResponse {
    public interface Response {}

    public record SuccessResponse(List<LearnSkillDtoResponse> response) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
