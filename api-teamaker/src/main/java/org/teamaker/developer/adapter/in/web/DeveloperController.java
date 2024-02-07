package org.teamaker.developer.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperCommand;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperResponse;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperUseCase;
import org.teamaker.developer.application.port.in.getDevelopers.GetDevelopersUseCase;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperResponse;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperUseCase;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperResponse;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperUseCase;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

@RestController
public class DeveloperController {
    private final GetDeveloperUseCase getDeveloperUseCase;
    private final ResignDeveloperUseCase resignDeveloperUseCase;
    private final GetDevelopersUseCase getDevelopersUseCase;
    private final HireDeveloperUseCase hireDeveloperUseCase;

    public DeveloperController(GetDeveloperUseCase getDeveloperUseCase, ResignDeveloperUseCase resignDeveloperUseCase, GetDevelopersUseCase getDevelopersUseCase, HireDeveloperUseCase hireDeveloperUseCase) {
        this.getDeveloperUseCase = getDeveloperUseCase;
        this.resignDeveloperUseCase = resignDeveloperUseCase;
        this.getDevelopersUseCase = getDevelopersUseCase;
        this.hireDeveloperUseCase = hireDeveloperUseCase;
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
}
