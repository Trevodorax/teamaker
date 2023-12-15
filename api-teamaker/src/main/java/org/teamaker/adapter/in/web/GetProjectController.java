package org.teamaker.adapter.in.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.teamaker.application.port.in.GetProjectUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamaker.domain.Project;

@RestController
public class GetProjectController {
    private final GetProjectUseCase getProjectUseCase;

    public GetProjectController(GetProjectUseCase getProjectUseCase) {
        this.getProjectUseCase = getProjectUseCase;
    }

    public void testArchitecture() {
        System.out.println("Should display the project id: " + getProjectUseCase.getProject("test-project"));
    }

    @GetMapping("/projects/{projectId}")
    Project getAccountBalance(@PathVariable("projectId") String projectId) {
        return getProjectUseCase.getProject(projectId);
    }
}
