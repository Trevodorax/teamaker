package org.teamaker.developer.application.port.out;

import org.teamaker.developer.domain.Developer;

import java.time.LocalDate;

public interface UpdateDeveloperPort {
    public LocalDate resignDeveloper(UpdateDeveloperResignationDateCommand command);
}
