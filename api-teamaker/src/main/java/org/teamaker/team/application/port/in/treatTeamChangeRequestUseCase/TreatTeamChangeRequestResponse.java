package org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase;

import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.team.domain.TeamChangeRequest;

import java.util.List;

public class TreatTeamChangeRequestResponse {
    public interface Response {}

    public record SuccessResponse(TeamChangeRequest teamChangeRequest) implements Response {}

    public record SingleErrorResponse(String errorMessage) implements Response {}

    public record MultipleErrorsResponse(List<String> errorMessages) implements Response {}
}


