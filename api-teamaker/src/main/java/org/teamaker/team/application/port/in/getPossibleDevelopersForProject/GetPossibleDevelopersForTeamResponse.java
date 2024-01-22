package org.teamaker.team.application.port.in.getPossibleDevelopersForProject;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

public class GetPossibleDevelopersForTeamResponse {
    public interface Response {}

    public record SuccessResponse(List<DeveloperResponse> developers) implements GetPossibleDevelopersForTeamResponse.Response {}

    public record ErrorResponse(String errorMessage) implements GetPossibleDevelopersForTeamResponse.Response {}

}
