package org.teamaker.technology.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyUseCase;

@RestController
public class TechnologyController {
    private final GetDevelopersByTechnologyUseCase getDevelopersByTechnologyUseCase;

    public TechnologyController(GetDevelopersByTechnologyUseCase getDevelopersByTechnologyUseCase) {
        this.getDevelopersByTechnologyUseCase = getDevelopersByTechnologyUseCase;
    }

    @GetMapping("/technologies/{id}/developers")
    public ResponseEntity<GetDevelopersByTechnologyResponse.Response> getDevelopersByTechnology(@PathVariable String id) {
        GetDevelopersByTechnologyResponse.Response response = getDevelopersByTechnologyUseCase
                .getDevelopersByTechnology(new GetDevelopersByTechnologyCommand(id));

        if (response instanceof GetDevelopersByTechnologyResponse.SuccessResponse) {
            return ResponseEntity.ok(new GetDevelopersByTechnologyResponse.SuccessResponse(
                    ((GetDevelopersByTechnologyResponse.SuccessResponse) response).developers()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GetDevelopersByTechnologyResponse.ErrorResponse(
                    ((GetDevelopersByTechnologyResponse.ErrorResponse) response).message()));
        }
    }
}
