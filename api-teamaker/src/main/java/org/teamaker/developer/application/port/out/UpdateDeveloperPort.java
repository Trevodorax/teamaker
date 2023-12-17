package org.teamaker.developer.application.port.out;

import org.teamaker.developer.application.port.in.ResignDeveloperCommand;
import org.teamaker.developer.domain.Developer;

public interface UpdateDeveloperPort {
    public Developer resignDeveloper(UpdateDeveloperCommand command);
}
