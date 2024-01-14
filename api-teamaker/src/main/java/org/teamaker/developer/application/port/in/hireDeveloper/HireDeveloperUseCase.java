package org.teamaker.developer.application.port.in.hireDeveloper;

import org.teamaker.developer.application.dto.DeveloperResponse;

public interface HireDeveloperUseCase {
    DeveloperResponse hireDeveloper(HireDeveloperCommand command);
}
