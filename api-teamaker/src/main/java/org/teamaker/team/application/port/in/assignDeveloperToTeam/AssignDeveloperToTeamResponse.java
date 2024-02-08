package org.teamaker.team.application.port.in.assignDeveloperToTeam;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public class AssignDeveloperToTeamResponse {
    public interface Response {}

    public record SuccessResponse(List<DeveloperResponse> developer) implements Response {}
    public record SingleErrorResponse(String errorMessage) implements Response {}
    public record MultipleErrorsResponse(List<String> errorMessage) implements Response {}
}
