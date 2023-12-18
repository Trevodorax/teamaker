package org.teamaker.developer.application.port.out;

import org.teamaker.developer.domain.Developer;

public interface UpdateDeveloperPort {
    public Developer resignDeveloper(UpdateDeveloperResignationDateCommand command);
    public Developer updateDeveloperInfo(UpdateDeveloperPersonalInfoCommand command);
}
