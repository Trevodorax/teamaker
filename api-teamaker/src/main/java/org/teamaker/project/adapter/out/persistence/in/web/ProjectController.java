package org.teamaker.project.adapter.out.persistence.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectResponse;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectUseCase;
import org.teamaker.project.application.port.in.getProject.GetProjectCommand;
import org.teamaker.project.application.port.in.getProject.GetProjectResponse;
import org.teamaker.project.application.port.in.getProject.GetProjectUseCase;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectUseCase;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;

@RestController
public class ProjectController {
    private final SubmitProjectUseCase submitProjectUseCase;
    private final GetNextProjectUseCase getNextProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;

    public ProjectController(SubmitProjectUseCase submitProjectUseCase, GetNextProjectUseCase getNextProjectUseCase, GetProjectUseCase getProjectUseCase) {
        this.submitProjectUseCase = submitProjectUseCase;
        this.getNextProjectUseCase = getNextProjectUseCase;
        this.getProjectUseCase = getProjectUseCase;
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

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<GetProjectResponse.Response> getProject(@PathVariable String projectId) {
        GetProjectResponse.Response response = getProjectUseCase.getProject(new GetProjectCommand(projectId));

        if (response instanceof GetProjectResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetProjectResponse.ErrorResponse(
                            ((GetProjectResponse.ErrorResponse) response).errorMessage()));
        }
    }
}
