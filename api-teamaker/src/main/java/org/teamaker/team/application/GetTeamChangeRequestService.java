package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestResponse;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestUseCase;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

@Component
public class GetTeamChangeRequestService implements GetTeamChangeRequestUseCase {
    private final LoadTeamChangeRequestPort loadTeamChangeRequestPort;

    public GetTeamChangeRequestService(LoadTeamChangeRequestPort loadTeamChangeRequest) {
        this.loadTeamChangeRequestPort = loadTeamChangeRequest;
    }

    @Override
    public GetTeamChangeRequestResponse.Response getTeamChangeRequest(GetTeamChangeRequestCommand command) {
        try {
            TeamChangeRequest request = loadTeamChangeRequestPort.loadTeamChangeRequest(
                    new LoadTeamChangeRequestCommand(command.getTeamChangeRequestId())
            );

            return new GetTeamChangeRequestResponse.SuccessResponse(request);
        } catch(IllegalArgumentException exception) {
            return new GetTeamChangeRequestResponse.ErrorResponse(exception.getMessage());
        }
    }
}

