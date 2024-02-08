package org.teamaker.project.adapter.out.persistence.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectResponse;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectUseCase;
import org.teamaker.project.application.port.in.getProject.GetProjectCommand;
import org.teamaker.project.application.port.in.getProject.GetProjectResponse;
import org.teamaker.project.application.port.in.getProject.GetProjectUseCase;
import org.teamaker.project.application.port.in.getProjects.GetProjectsResponse;
import org.teamaker.project.application.port.in.getProjects.GetProjectsUseCase;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectCommand;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectRequest;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectResponse;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectUseCase;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectCommand;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectResponse;
import org.teamaker.project.application.port.in.submitProject.SubmitProjectUseCase;
import org.teamaker.project.application.port.in.treatProject.TreatProjectCommand;
import org.teamaker.project.application.port.in.treatProject.TreatProjectRequest;
import org.teamaker.project.application.port.in.treatProject.TreatProjectResponse;
import org.teamaker.project.application.port.in.treatProject.TreatProjectUseCase;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoCommand;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoRequest;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoResponse;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoUseCase;

@RestController
public class ProjectController {
    private final SubmitProjectUseCase submitProjectUseCase;
    private final GetNextProjectUseCase getNextProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;
    private final GetProjectsUseCase getProjectsUseCase;
    private final UpdateProjectInfoUseCase updateProjectInfoUseCase;
    private final TreatProjectUseCase treatProjectUseCase;
    private final PostponeProjectUseCase postponeProjectUseCase;

    public ProjectController(SubmitProjectUseCase submitProjectUseCase, GetNextProjectUseCase getNextProjectUseCase, GetProjectUseCase getProjectUseCase, GetProjectsUseCase getProjectsUseCase, UpdateProjectInfoUseCase updateProjectInfoUseCase, TreatProjectUseCase treatProjectUseCase, PostponeProjectUseCase postponeProjectUseCase) {
        this.submitProjectUseCase = submitProjectUseCase;
        this.getNextProjectUseCase = getNextProjectUseCase;
        this.getProjectUseCase = getProjectUseCase;
        this.getProjectsUseCase = getProjectsUseCase;
        this.updateProjectInfoUseCase = updateProjectInfoUseCase;
        this.treatProjectUseCase = treatProjectUseCase;
        this.postponeProjectUseCase = postponeProjectUseCase;
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

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<UpdateProjectInfoResponse.Response> updateProjectInfo(@PathVariable String projectId, @RequestBody UpdateProjectInfoRequest command) {
        UpdateProjectInfoResponse.Response response = updateProjectInfoUseCase.updateProjectInfo(new UpdateProjectInfoCommand(projectId, command.getName(), command.getDescription(), command.getPriority()));

        if (response instanceof UpdateProjectInfoResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UpdateProjectInfoResponse.ErrorResponse(
                            ((UpdateProjectInfoResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<GetProjectsResponse.Response> getProjects() {
        GetProjectsResponse.Response response = getProjectsUseCase.getProjects();

        if (response instanceof GetProjectsResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetProjectsResponse.ErrorResponse(
                            ((GetProjectsResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @PatchMapping("/projects/{projectId}/treat")
    public ResponseEntity<TreatProjectResponse.Response> treatProject(@PathVariable String projectId, @RequestBody TreatProjectRequest command) {
        TreatProjectResponse.Response response = treatProjectUseCase.treatProject(new TreatProjectCommand(projectId, command.getStatus()));

        if (response instanceof TreatProjectResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new TreatProjectResponse.ErrorResponse(
                            ((TreatProjectResponse.ErrorResponse) response).message()));
        }
    }

    @PatchMapping("/projects/{projectId}/postpone")
    public ResponseEntity<PostponeProjectResponse.Response> postponeProject(@PathVariable String projectId, @RequestBody PostponeProjectRequest command) {
        PostponeProjectResponse.Response response = postponeProjectUseCase.postponeProject(new PostponeProjectCommand(projectId, command.getNewStartDate(), command.getNewEndDate()));

        if (response instanceof PostponeProjectResponse.SuccessResponse) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new PostponeProjectResponse.ErrorResponse(
                            ((PostponeProjectResponse.ErrorResponse) response).errorMessage()));
        }
    }
}
