package org.teamaker.developer.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamaker.developer.application.port.in.forgetSkill.ForgetSkillCommand;
import org.teamaker.developer.application.port.in.forgetSkill.ForgetSkillResponse;
import org.teamaker.developer.application.port.in.forgetSkill.ForgetSkillUseCase;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperCommand;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperResponse;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperUseCase;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsCommand;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsResponse;
import org.teamaker.developer.application.port.in.getDeveloperProjects.GetDeveloperProjectsUseCase;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsCommand;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsResponse;
import org.teamaker.developer.application.port.in.getDeveloperSkills.GetDeveloperSkillsUseCase;
import org.teamaker.developer.application.port.in.getDevelopers.GetDevelopersUseCase;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperResponse;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperUseCase;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillCommand;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillResponse;
import org.teamaker.developer.application.port.in.learnSkill.LearnSkillUseCase;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperResponse;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperUseCase;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoCommand;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoRequest;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoResponse;
import org.teamaker.developer.application.port.in.updateDeveloperInfo.UpdateDeveloperInfoUseCase;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {
    private final GetDeveloperUseCase getDeveloperUseCase;
    private final ResignDeveloperUseCase resignDeveloperUseCase;
    private final GetDevelopersUseCase getDevelopersUseCase;
    private final HireDeveloperUseCase hireDeveloperUseCase;
    private final UpdateDeveloperInfoUseCase updateDeveloperInfoUseCase;
    private final LearnSkillUseCase learnSkillUseCase;
    private final GetDeveloperSkillsUseCase getDeveloperSkillsUseCase;
    private final ForgetSkillUseCase forgetSkillUseCase;
    private final GetDeveloperProjectsUseCase getDeveloperProjectsUseCase;

    public DeveloperController(GetDeveloperUseCase getDeveloperUseCase, ResignDeveloperUseCase resignDeveloperUseCase, GetDevelopersUseCase getDevelopersUseCase, HireDeveloperUseCase hireDeveloperUseCase, UpdateDeveloperInfoUseCase updateDeveloperInfoUseCase, LearnSkillUseCase learnSkillUseCase, GetDeveloperSkillsUseCase getDeveloperSkillsUseCase, ForgetSkillUseCase forgetSkillUseCase, GetDeveloperProjectsUseCase getDeveloperProjectsUseCase) {
        this.getDeveloperUseCase = getDeveloperUseCase;
        this.resignDeveloperUseCase = resignDeveloperUseCase;
        this.getDevelopersUseCase = getDevelopersUseCase;
        this.hireDeveloperUseCase = hireDeveloperUseCase;
        this.updateDeveloperInfoUseCase = updateDeveloperInfoUseCase;
        this.learnSkillUseCase = learnSkillUseCase;
        this.getDeveloperSkillsUseCase = getDeveloperSkillsUseCase;
        this.forgetSkillUseCase = forgetSkillUseCase;
        this.getDeveloperProjectsUseCase = getDeveloperProjectsUseCase;
    }

    @GetMapping("/developers")
    public List<DeveloperResponse> getDevelopers() {
        return getDevelopersUseCase.getDevelopers();
    }

    @PostMapping("/developers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HireDeveloperResponse.Response> hireDeveloper(@RequestBody HireDeveloperCommand command) {
        HireDeveloperResponse.Response response = hireDeveloperUseCase.hireDeveloper(command);

        if (response instanceof HireDeveloperResponse.SuccessResponse) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new HireDeveloperResponse.SuccessResponse(
                            ((HireDeveloperResponse.SuccessResponse) response).developer()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new HireDeveloperResponse.ErrorResponse(
                            ((HireDeveloperResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @GetMapping("/developers/{id}")
    public ResponseEntity<GetDeveloperResponse.Response> getDeveloper(@PathVariable String id) {
        GetDeveloperResponse.Response response = getDeveloperUseCase.getDeveloper(new GetDeveloperCommand(id));

        if (response instanceof GetDeveloperResponse.SuccessResponse) {
            return ResponseEntity
                    .ok(new GetDeveloperResponse.SuccessResponse(
                            ((GetDeveloperResponse.SuccessResponse) response).developer()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetDeveloperResponse.ErrorResponse(
                            ((GetDeveloperResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @DeleteMapping("/developers/{id}")
    public ResponseEntity<ResignDeveloperResponse.Response> resignDeveloper(@PathVariable String id) {
        ResignDeveloperResponse.Response response = resignDeveloperUseCase.resignDeveloper(new ResignDeveloperCommand(id));

        if (response instanceof ResignDeveloperResponse.SuccessResponse) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResignDeveloperResponse.SuccessResponse(
                            ((ResignDeveloperResponse.SuccessResponse) response).resignationDate()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResignDeveloperResponse.ErrorResponse(
                            ((ResignDeveloperResponse.ErrorResponse) response).errorMessage()));
        }
    }

    @PatchMapping("/developers/{id}")
    public ResponseEntity<UpdateDeveloperInfoResponse.Response> updateDeveloperInfo(@PathVariable String id, @RequestBody UpdateDeveloperInfoRequest developer) {
        UpdateDeveloperInfoResponse.Response response = updateDeveloperInfoUseCase.updateDeveloperInfo(new UpdateDeveloperInfoCommand(id, developer.getFullName(), developer.getEmail()));

        if (response instanceof UpdateDeveloperInfoResponse.SuccessResponse) {
            return ResponseEntity
                    .ok(new UpdateDeveloperInfoResponse.SuccessResponse(
                            ((UpdateDeveloperInfoResponse.SuccessResponse) response).developer()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new UpdateDeveloperInfoResponse.ErrorResponse(
                            ((UpdateDeveloperInfoResponse.ErrorResponse) response).message()));
        }
    }

    @PostMapping("/developers/{developerId}/skills/{technologyId}")
    public ResponseEntity<LearnSkillResponse.Response> learnSkill(@PathVariable String developerId, @PathVariable String technologyId) {
        LearnSkillResponse.Response response = learnSkillUseCase.learnSkill(new LearnSkillCommand(developerId, technologyId));

        return switch (response) {
            case LearnSkillResponse.SuccessResponse successResponse -> ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(successResponse);
            case LearnSkillResponse.ErrorResponseNotFound errorResponse -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
            case LearnSkillResponse.ErrorResponseConflict errorResponse -> ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(errorResponse);
            case null, default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        };
    }

    @GetMapping("/developers/{developerId}/skills")
    public ResponseEntity<GetDeveloperSkillsResponse.Response> getDeveloperSkills(@PathVariable String developerId) {
        GetDeveloperSkillsResponse.Response response = getDeveloperSkillsUseCase.getDeveloperSkills(new GetDeveloperSkillsCommand(developerId));

        if (response instanceof GetDeveloperSkillsResponse.SuccessResponse successResponse) {
            return ResponseEntity
                    .ok(successResponse);
        } else {
            GetDeveloperSkillsResponse.ErrorResponse errorResponse = (GetDeveloperSkillsResponse.ErrorResponse) response;
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @DeleteMapping("/developers/{developerId}/skills/{technologyId}")
    public ResponseEntity<ForgetSkillResponse.Response> forgetSkill(@PathVariable String developerId, @PathVariable String technologyId) {
        ForgetSkillResponse.Response response = forgetSkillUseCase.forgetSkill(new ForgetSkillCommand(developerId, technologyId));

        if (response instanceof ForgetSkillResponse.SuccessResponse successResponse) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(successResponse);
        } else {
            ForgetSkillResponse.ErrorResponse errorResponse = (ForgetSkillResponse.ErrorResponse) response;
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @GetMapping("/developers/{developerId}/projects")
    public ResponseEntity<GetDeveloperProjectsResponse.Response> getDeveloperProjects(@PathVariable String developerId) {
        GetDeveloperProjectsResponse.Response response = getDeveloperProjectsUseCase.getDeveloperProjects(new GetDeveloperProjectsCommand(developerId));

        if (response instanceof GetDeveloperProjectsResponse.SuccessResponse successResponse) {
            return ResponseEntity
                    .ok(successResponse);
        } else {
            GetDeveloperProjectsResponse.ErrorResponse errorResponse = (GetDeveloperProjectsResponse.ErrorResponse) response;
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

}
