package org.teamaker.developer.application.port.in.updateDeveloperInfo;

import org.teamaker.developer.domain.dto.DeveloperResponse;

public interface UpdateDeveloperInfoUseCase {
    DeveloperResponse updateDeveloperInfo(UpdateDeveloperInfoCommand command);
}
