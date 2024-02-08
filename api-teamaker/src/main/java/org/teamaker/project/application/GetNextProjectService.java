package org.teamaker.project.application;

import org.springframework.stereotype.Component;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectResponse;
import org.teamaker.project.application.port.in.getNextProject.GetNextProjectUseCase;
import org.teamaker.project.application.port.out.findNextProject.FindNextProjectPort;

import java.util.NoSuchElementException;

@Component
public class GetNextProjectService implements GetNextProjectUseCase {
    private final FindNextProjectPort findNextProjectPort;

    public GetNextProjectService(FindNextProjectPort findNextProjectPort) {
        this.findNextProjectPort = findNextProjectPort;
    }

    @Override
    public GetNextProjectResponse.Response getNextProject() {
        try {
            return new GetNextProjectResponse.SuccessResponse(findNextProjectPort.findNextProject().toResponse());
        } catch(NoSuchElementException exception) {
            return new GetNextProjectResponse.ErrorResponse(exception.getMessage());
        }
    }
}
