package org.teamaker.developer.application.port.in;

import org.teamaker.developer.domain.Developer;

public interface HireDeveloperUseCase {
    public Developer hireDeveloper(HireDeveloperCommand command);
}
