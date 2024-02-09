package org.teamaker.team.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamaker.team.application.AssignDeveloperToTeamService;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamCommand;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamResponse;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamUseCase;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForProjectCommand;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForProjectUseCase;
import org.teamaker.team.application.port.in.getPossibleDevelopersForProject.GetPossibleDevelopersForTeamResponse;
import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.in.getTeam.GetTeamResponse;
import org.teamaker.team.application.port.in.getTeam.GetTeamUseCase;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestResponse;
import org.teamaker.team.application.port.in.getTeamChangeRequest.GetTeamChangeRequestUseCase;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamResponse;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamUseCase;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestResponse;
import org.teamaker.team.application.port.in.submitTeamChangeRequest.SubmitTeamChangeRequestUseCase;

@RestController
public class TeamController {
    private final GetTeamUseCase getTeamUseCase;
    private final GetPossibleDevelopersForProjectUseCase getPossibleDevelopersForProjectUseCase;
    private final AssignDeveloperToTeamUseCase assignDeveloperToTeamUseCase;
    private final RemoveDeveloperFromTeamUseCase removeDeveloperFromTeamUseCase;
    private final SubmitTeamChangeRequestUseCase submitTeamChangeRequestUseCase;
    private final GetTeamChangeRequestUseCase getTeamChangeRequestUseCase;

    public TeamController(GetTeamUseCase getTeamUseCase, GetPossibleDevelopersForProjectUseCase getPossibleDevelopersForProjectUseCase, AssignDeveloperToTeamUseCase assignDeveloperToTeamUseCase, RemoveDeveloperFromTeamUseCase removeDeveloperFromTeamUseCase, SubmitTeamChangeRequestUseCase submitTeamChangeRequestUseCase, GetTeamChangeRequestUseCase getTeamChangeRequestUseCase) {
        this.getTeamUseCase = getTeamUseCase;
        this.getPossibleDevelopersForProjectUseCase = getPossibleDevelopersForProjectUseCase;
        this.assignDeveloperToTeamUseCase = assignDeveloperToTeamUseCase;
        this.removeDeveloperFromTeamUseCase = removeDeveloperFromTeamUseCase;
        this.submitTeamChangeRequestUseCase = submitTeamChangeRequestUseCase;
        this.getTeamChangeRequestUseCase = getTeamChangeRequestUseCase;
    }

    @GetMapping("/projects/{projectId}/developers")
    public ResponseEntity<GetTeamResponse.Response> loadTeam(@PathVariable String projectId) {
        GetTeamResponse.Response response = getTeamUseCase.getTeam(new GetTeamCommand(projectId));

        if (response instanceof GetTeamResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetTeamResponse.ErrorResponse(
                            ((GetTeamResponse.ErrorResponse) response).message()));
        }
    }

    @PostMapping("/projects/{projectId}/developers/{developerId}")
    public ResponseEntity<AssignDeveloperToTeamResponse.Response> assignDeveloperToTeam(@PathVariable String projectId, @PathVariable String developerId) {
        AssignDeveloperToTeamResponse.Response response = assignDeveloperToTeamUseCase.assignDeveloperToTeam(new AssignDeveloperToTeamCommand(developerId, projectId));

        return switch (response) {
            case AssignDeveloperToTeamResponse.SuccessResponse successResponse -> ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(successResponse);
            case AssignDeveloperToTeamResponse.SingleErrorResponse singleErrorResponse -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new AssignDeveloperToTeamResponse.SingleErrorResponse(
                            singleErrorResponse.errorMessage()));
            case AssignDeveloperToTeamResponse.MultipleErrorsResponse multipleErrorsResponse -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new AssignDeveloperToTeamResponse.MultipleErrorsResponse(
                            multipleErrorsResponse.errorMessage()));
            case null, default -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new AssignDeveloperToTeamResponse.SingleErrorResponse("Unknown error"));
        };
    }

    @DeleteMapping("/projects/{projectId}/developers/{developerId}")
    public ResponseEntity<RemoveDeveloperFromTeamResponse.Response> removeDeveloperFromTeam(@PathVariable String projectId, @PathVariable String developerId) {
        RemoveDeveloperFromTeamResponse.Response response = removeDeveloperFromTeamUseCase.removeDeveloperFromTeam(new org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamCommand(developerId, projectId));

        return switch (response) {
            case RemoveDeveloperFromTeamResponse.SuccessResponse successResponse -> ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(successResponse);
            case RemoveDeveloperFromTeamResponse.SingleErrorResponse singleErrorResponse -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RemoveDeveloperFromTeamResponse.SingleErrorResponse(
                            singleErrorResponse.errorMessage()));
            case RemoveDeveloperFromTeamResponse.MultipleErrorsResponse multipleErrorsResponse -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RemoveDeveloperFromTeamResponse.MultipleErrorsResponse(
                            multipleErrorsResponse.errorMessages()));
            case null, default -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RemoveDeveloperFromTeamResponse.SingleErrorResponse("Unknown error"));
        };
    }


    @GetMapping("/projects/{projectId}/developersAvailable")
    public ResponseEntity<GetPossibleDevelopersForTeamResponse.Response> getPossibleDevelopersForProject(@PathVariable String projectId) {
        GetPossibleDevelopersForTeamResponse.Response response = getPossibleDevelopersForProjectUseCase.getPossibleDevelopersForProject(new GetPossibleDevelopersForProjectCommand(projectId));

        if (response instanceof GetPossibleDevelopersForTeamResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetPossibleDevelopersForTeamResponse.ErrorResponse(
                            ((GetPossibleDevelopersForTeamResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @PostMapping("/teamChangeRequest")
    public ResponseEntity<SubmitTeamChangeRequestResponse.Response> submitTeamChangeRequest(@RequestBody SubmitTeamChangeRequestCommand submitTeamChangeRequestCommand) {
        SubmitTeamChangeRequestResponse.Response response = submitTeamChangeRequestUseCase.submitTeamChangeRequest(submitTeamChangeRequestCommand);

        if (response instanceof SubmitTeamChangeRequestResponse.SuccessResponse) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new SubmitTeamChangeRequestResponse.ErrorResponse(
                            ((SubmitTeamChangeRequestResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @GetMapping("/teamChangeRequest/{teamChangeRequestId}")
    public ResponseEntity<GetTeamChangeRequestResponse.Response> getTeamChangeRequest(@PathVariable String teamChangeRequestId) {
        GetTeamChangeRequestResponse.Response response = getTeamChangeRequestUseCase.getTeamChangeRequest(new GetTeamChangeRequestCommand(teamChangeRequestId));

        if (response instanceof GetTeamChangeRequestResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetTeamChangeRequestResponse.ErrorResponse(
                            ((GetTeamChangeRequestResponse.ErrorResponse) response).errorMessage()));
        }
    }
}
