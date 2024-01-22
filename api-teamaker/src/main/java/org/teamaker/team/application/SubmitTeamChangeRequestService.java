package org.teamaker.team.application;

import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestResponse;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestUseCase;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestPort;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsCommand;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsPort;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

import java.time.LocalDate;
import java.util.List;

public class SubmitTeamChangeRequestService implements SubmitTeamChangeRequestUseCase {
    private final LoadDeveloperTeamChangeRequestsPort loadDeveloperTeamChangeRequests;
    private final CreateTeamChangeRequestPort createTeamChangeRequestPort;

    public SubmitTeamChangeRequestService(LoadDeveloperTeamChangeRequestsPort loadDeveloperTeamChangeRequests, CreateTeamChangeRequestPort createTeamChangeRequestPort) {
        this.loadDeveloperTeamChangeRequests = loadDeveloperTeamChangeRequests;
        this.createTeamChangeRequestPort = createTeamChangeRequestPort;
    }

    @Override
    public SubmitTeamChangeRequestResponse.Response submitTeamChangeRequest(SubmitTeamChangeRequestCommand command) {
        // get developer team change requests
        List<TeamChangeRequest> teamChangeRequests;
        try {
            teamChangeRequests = loadDeveloperTeamChangeRequests.loadDeveloperTeamChangeRequests(
                    new LoadDeveloperTeamChangeRequestsCommand(command.getDeveloperId())
            );
        } catch(IllegalArgumentException exception) {
            return new SubmitTeamChangeRequestResponse.ErrorResponse(exception.getMessage());
        }

        // check he can submit one
        boolean hasRecentTeamChangeRequest = teamChangeRequests.stream()
                .anyMatch(teamChangeRequest -> teamChangeRequest.getSubmitDate().isAfter(LocalDate.now().minusMonths(6)));
        if(hasRecentTeamChangeRequest) {
            return new SubmitTeamChangeRequestResponse.ErrorResponse("Developer already asked to switch teams less than 6 months ago.");
        }

        // save the teamChangeRequest and return the return of save
        TeamChangeRequest createdTeamChangeRequest = createTeamChangeRequestPort.createTeamChangeRequest(
                new CreateTeamChangeRequestCommand(command.getDeveloperId(), command.getRequestedProjectId())
        );

        return new SubmitTeamChangeRequestResponse.SuccessResponse(createdTeamChangeRequest);
    }
}
