package org.teamaker.developer.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperCommand;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperResponse;
import org.teamaker.developer.application.port.in.hireDeveloper.HireDeveloperUseCase;

@RestController
public class HireDeveloperController {
    private final HireDeveloperUseCase hireDeveloperUseCase;

    public HireDeveloperController(HireDeveloperUseCase hireDeveloperUseCase) {
        this.hireDeveloperUseCase = hireDeveloperUseCase;
    }

    @PostMapping("/developers")
    @ResponseStatus(HttpStatus.CREATED)
    public HireDeveloperResponse.Response hireDeveloper(@RequestBody HireDeveloperCommand command) {
        return hireDeveloperUseCase.hireDeveloper(command);
    }
}
