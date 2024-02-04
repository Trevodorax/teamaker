package org.teamaker.developer.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperCommand;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperResponse;
import org.teamaker.developer.application.port.in.getDeveloper.GetDeveloperUseCase;

@RestController
public class GetDeveloperController {
    private final GetDeveloperUseCase getDeveloperUseCase;

    public GetDeveloperController(GetDeveloperUseCase getDeveloperUseCase) {
        this.getDeveloperUseCase = getDeveloperUseCase;
    }

    @GetMapping("/developers/{id}")
    public GetDeveloperResponse.Response getDeveloper(@PathVariable String id) {
        return getDeveloperUseCase.getDeveloper(new GetDeveloperCommand(id));
    }
}
