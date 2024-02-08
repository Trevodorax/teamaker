package org.teamaker.team.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.team.application.port.in.getTeam.GetTeamCommand;
import org.teamaker.team.application.port.in.getTeam.GetTeamResponse;
import org.teamaker.team.application.port.in.getTeam.GetTeamUseCase;

@RestController
public class TeamController {
    private final GetTeamUseCase getTeamUseCase;

    public TeamController(GetTeamUseCase getTeamUseCase) {
        this.getTeamUseCase = getTeamUseCase;
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

}
