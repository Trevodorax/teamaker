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
import org.teamaker.technology.application.port.in.getTechnologies.GetTechnologiesUseCase;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyCommand;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyResponse;
import org.teamaker.technology.application.port.in.getTechnology.GetTechnologyUseCase;
import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.util.List;

@RestController
public class TechnologyController {
    private final GetDevelopersByTechnologyUseCase getDevelopersByTechnologyUseCase;
    private final AddTechnologyUseCase addTechnologyUseCase;
    private final GetTechnologyUseCase getTechnologyUseCase;
    private final GetTechnologiesUseCase getTechnologiesUseCase;

    public TechnologyController(GetDevelopersByTechnologyUseCase getDevelopersByTechnologyUseCase, AddTechnologyUseCase addTechnologyUseCase, GetTechnologyUseCase getTechnologyUseCase, GetTechnologiesUseCase getTechnologiesUseCase) {
        this.getDevelopersByTechnologyUseCase = getDevelopersByTechnologyUseCase;
        this.addTechnologyUseCase = addTechnologyUseCase;
        this.getTechnologyUseCase = getTechnologyUseCase;
        this.getTechnologiesUseCase = getTechnologiesUseCase;
    }

    @GetMapping("/technologies")
    public List<TechnologyResponse> getTechnologies() {
        return getTechnologiesUseCase.getTechnologies();
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
                    .status(HttpStatus.CONFLICT)
                    .body(new AddTechnologyResponse.ErrorResponse(
                            ((AddTechnologyResponse.ErrorResponse) response).message()));
        }
    }

    @GetMapping("/technologies/{id}")
    public ResponseEntity<GetTechnologyResponse.Response> getTechnology(@PathVariable String id) {
        GetTechnologyResponse.Response response = getTechnologyUseCase.getTechnology(new GetTechnologyCommand(id));

        if (response instanceof GetTechnologyResponse.SuccessResponse) {
            return ResponseEntity
                    .ok(new GetTechnologyResponse.SuccessResponse(
                            ((GetTechnologyResponse.SuccessResponse) response).technology()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GetTechnologyResponse.ErrorResponse(
                            ((GetTechnologyResponse.ErrorResponse) response).message()));
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
