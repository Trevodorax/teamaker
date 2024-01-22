package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public class SubmitTeamChangeRequestResponse {
    public interface Response {}

    public record SuccessResponse(TeamChangeRequest teamChangeRequest) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
