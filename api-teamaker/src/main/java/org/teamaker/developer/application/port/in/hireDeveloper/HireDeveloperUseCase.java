package org.teamaker.developer.application.port.in.hireDeveloper;

import org.teamaker.developer.domain.Developer;

public interface HireDeveloperUseCase {
    Developer hireDeveloper(HireDeveloperCommand command);
}
