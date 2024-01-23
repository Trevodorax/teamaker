package org.teamaker.team.application.port.in.getTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public class GetTeamChangeRequestResponse {
    public interface Response {}

    public record SuccessResponse(TeamChangeRequest request) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
