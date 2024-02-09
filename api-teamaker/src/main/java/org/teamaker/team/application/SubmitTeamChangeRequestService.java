package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestResponse;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestUseCase;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.createTeamChangeRequest.CreateTeamChangeRequestPort;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsCommand;
import org.teamaker.team.application.port.out.loadDeveloperTeamChangeRequests.LoadDeveloperTeamChangeRequestsPort;
import org.teamaker.team.domain.TeamChangeRequest;

import java.time.LocalDate;
import java.util.List;

@Component
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
        List<TeamChangeRequest> teamChangeRequests = loadDeveloperTeamChangeRequests
                .loadDeveloperTeamChangeRequests(new LoadDeveloperTeamChangeRequestsCommand(command.getDeveloperId()));

        if (!teamChangeRequests.isEmpty()) {
            // check he can submit one
            boolean hasRecentTeamChangeRequest = teamChangeRequests.stream()
                    .anyMatch(teamChangeRequest -> teamChangeRequest.getSubmitDate().isAfter(LocalDate.now().minusMonths(6)));
            if(hasRecentTeamChangeRequest) {
                return new SubmitTeamChangeRequestResponse.ErrorResponse("Developer already asked to switch teams less than 6 months ago.");
            }
        }

        // save the teamChangeRequest and return the return of save
        try {
            TeamChangeRequest createdTeamChangeRequest = createTeamChangeRequestPort.createTeamChangeRequest(
                    new CreateTeamChangeRequestCommand(command.getDeveloperId(), command.getRequestedProjectId(), command.getFromProjectId())
            );
            return new SubmitTeamChangeRequestResponse.SuccessResponse(createdTeamChangeRequest);
        } catch (IllegalArgumentException e) {
            return new SubmitTeamChangeRequestResponse.ErrorResponse(e.getMessage());
        }
    }
}
