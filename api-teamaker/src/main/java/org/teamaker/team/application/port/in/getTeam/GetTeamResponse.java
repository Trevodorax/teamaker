package org.teamaker.team.application.port.in.getTeam;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public class GetTeamResponse {
    public interface Response {}

    public record SuccessResponse(List<DeveloperResponse> team) implements Response {}

    public record ErrorResponse(String message) implements Response {}
}
