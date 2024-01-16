package org.teamaker.developer.application.port.in.hireDeveloper;

import org.teamaker.developer.domain.dto.DeveloperResponse;

public interface HireDeveloperUseCase {
    DeveloperResponse hireDeveloper(HireDeveloperCommand command);
}
