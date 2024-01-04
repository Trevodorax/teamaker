package org.teamaker.developer.application.port.out;

import java.util.Objects;

public record FindDevelopersByTechnologyCommand(String technologyId) {
    public FindDevelopersByTechnologyCommand {
        Objects.requireNonNull(technologyId, "technologyId must not be null");
    }
}