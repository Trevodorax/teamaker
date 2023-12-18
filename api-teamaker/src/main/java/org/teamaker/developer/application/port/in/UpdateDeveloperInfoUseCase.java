package org.teamaker.developer.application.port.in;

import org.teamaker.developer.domain.Developer;

public interface UpdateDeveloperInfoUseCase {
    public Developer updateDeveloperInfo(UpdateDeveloperInfoCommand command);
}
