package org.teamaker.project.adapter.out.persistence.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectResponse;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectUseCase;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectUseCase;

@RestController
public class ProjectController {
    private final SubmitProjectUseCase submitProjectUseCase;
    private final GetNextProjectUseCase getNextProjectUseCase;

    public ProjectController(SubmitProjectUseCase submitProjectUseCase, GetNextProjectUseCase getNextProjectUseCase) {
        this.submitProjectUseCase = submitProjectUseCase;
        this.getNextProjectUseCase = getNextProjectUseCase;
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

    @GetMapping("/projects/next")
    public ResponseEntity<GetNextProjectResponse.Response> getNextProject() {
        GetNextProjectResponse.Response response = getNextProjectUseCase.getNextProject();

        if (response instanceof GetNextProjectResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetNextProjectResponse.ErrorResponse(
                            ((GetNextProjectResponse.ErrorResponse) response).errorMessage()));
        }
    }
}
