package org.teamaker.developer.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.developer.application.port.in.getDevelopers.GetDevelopersUseCase;
import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.util.List;

@RestController
public class GetDevelopersController {
    private final GetDevelopersUseCase getDevelopersUseCase;

    public GetDevelopersController(GetDevelopersUseCase getDevelopersUseCase) {
        this.getDevelopersUseCase = getDevelopersUseCase;
    }

    @GetMapping("/developers")
    public List<DeveloperResponse> getDevelopers() {
        return getDevelopersUseCase.getDevelopers();
    }
}
