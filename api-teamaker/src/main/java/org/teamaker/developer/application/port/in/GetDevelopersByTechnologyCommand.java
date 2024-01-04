package org.teamaker.developer.application.port.in;

import java.util.Objects;

public record GetDevelopersByTechnologyCommand(String technologyId) {
    public GetDevelopersByTechnologyCommand {
        Objects.requireNonNull(technologyId, "technologyId must not be null");
    }
}