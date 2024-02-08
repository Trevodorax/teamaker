package org.teamaker.technology.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyCommand;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyResponse;
import org.teamaker.developer.application.port.in.getDevelopersByTechnology.GetDevelopersByTechnologyUseCase;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyCommand;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyResponse;
import org.teamaker.technology.application.port.in.addTechnology.AddTechnologyUseCase;

@RestController
public class TechnologyController {
    private final GetDevelopersByTechnologyUseCase getDevelopersByTechnologyUseCase;
    private final AddTechnologyUseCase addTechnologyUseCase;

    public TechnologyController(GetDevelopersByTechnologyUseCase getDevelopersByTechnologyUseCase, AddTechnologyUseCase addTechnologyUseCase) {
        this.getDevelopersByTechnologyUseCase = getDevelopersByTechnologyUseCase;
        this.addTechnologyUseCase = addTechnologyUseCase;
    }

    @PostMapping("/technologies")
    public ResponseEntity<AddTechnologyResponse.Response> createTechnology(@RequestBody AddTechnologyCommand command) {
        AddTechnologyResponse.Response response = addTechnologyUseCase.addTechnology(command);

        if (response instanceof AddTechnologyResponse.SuccessResponse) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new AddTechnologyResponse.SuccessResponse(
                            ((AddTechnologyResponse.SuccessResponse) response).technology()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new AddTechnologyResponse.ErrorResponse(
                            ((AddTechnologyResponse.ErrorResponse) response).message()));
        }
    }

    @GetMapping("/technologies/{id}/developers")
    public ResponseEntity<GetDevelopersByTechnologyResponse.Response> getDevelopersByTechnology(@PathVariable String id) {
        GetDevelopersByTechnologyResponse.Response response = getDevelopersByTechnologyUseCase
                .getDevelopersByTechnology(new GetDevelopersByTechnologyCommand(id));

        if (response instanceof GetDevelopersByTechnologyResponse.SuccessResponse) {
            return ResponseEntity
                    .ok(new GetDevelopersByTechnologyResponse.SuccessResponse(
                            ((GetDevelopersByTechnologyResponse.SuccessResponse) response).developers()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetDevelopersByTechnologyResponse.ErrorResponse(
                            ((GetDevelopersByTechnologyResponse.ErrorResponse) response).message()));
        }
    }
}
