package org.teamaker.team.application.port.in.submitTeamChangeRequest;

import org.teamaker.team.domain.dto.TeamChangeRequestResponse;

public class SubmitTeamChangeRequestResponse {
    public interface Response {}

    public record SuccessResponse(TeamChangeRequestResponse teamChangeRequest) implements Response {}

    public record ErrorResponse(String errorMessage) implements Response {}
}
