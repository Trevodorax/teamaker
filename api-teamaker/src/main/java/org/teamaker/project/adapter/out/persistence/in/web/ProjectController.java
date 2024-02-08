package org.teamaker.project.adapter.out.persistence.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectUseCase;

@RestController
public class ProjectController {
    private final SubmitProjectUseCase submitProjectUseCase;

    public ProjectController(SubmitProjectUseCase submitProjectUseCase) {
        this.submitProjectUseCase = submitProjectUseCase;
    }

    @PostMapping("/projects")
    public ResponseEntity<SubmitProjectResponse.Response> submitProject(@RequestBody SubmitProjectCommand command) {
        SubmitProjectResponse.Response response = submitProjectUseCase.submitProject(command);

        if (response instanceof SubmitProjectResponse.SuccessResponse) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SubmitProjectResponse.ErrorResponse(
                            ((SubmitProjectResponse.ErrorResponse) response).message()));
        }
    }
}
