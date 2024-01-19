package org.teamaker.team.application.port.in.removeDeveloperFromTeam;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public class RemoveDeveloperFromTeamResponse {
    public interface Response {}

    public record SuccessResponse(DeveloperResponse developer) implements Response {}

    public record SingleErrorResponse(String errorMessage) implements Response {}

    public record MultipleErrorsResponse(List<String> errorMessages) implements Response {}
}


