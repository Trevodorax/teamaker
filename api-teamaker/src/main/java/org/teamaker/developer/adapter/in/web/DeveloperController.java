package org.teamaker.developer.adapter.in.web;

import org.springframework.http.HttpStatus;
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
    public HireDeveloperResponse.Response hireDeveloper(@RequestBody HireDeveloperCommand command) {
        return hireDeveloperUseCase.hireDeveloper(command);
    }

    @GetMapping("/developers/{id}")
    public GetDeveloperResponse.Response getDeveloper(@PathVariable String id) {
        return getDeveloperUseCase.getDeveloper(new GetDeveloperCommand(id));
    }

    @DeleteMapping("/developers/{id}")
    public ResignDeveloperResponse.Response resignDeveloper(@PathVariable String id) {
        return resignDeveloperUseCase.resignDeveloper(new ResignDeveloperCommand(id));
    }
}
