package org.teamaker.developer.application.port.out.updateDeveloper;

import java.time.LocalDate;

public interface UpdateDeveloperPort {
    LocalDate resignDeveloper(UpdateDeveloperResignationDateCommand command);
}
