package org.teamaker.developer.application.port.in.getDeveloperInfo;

import org.teamaker.developer.domain.dto.DeveloperResponse;

public interface GetDeveloperInfoUseCase {
    DeveloperResponse getDeveloperInfo(GetDeveloperInfoCommand command);
}
