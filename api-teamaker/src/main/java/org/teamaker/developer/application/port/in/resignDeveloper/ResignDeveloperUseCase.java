package org.teamaker.developer.application.port.in.resignDeveloper;

import java.time.LocalDate;

public interface ResignDeveloperUseCase {
    LocalDate resignDeveloper(ResignDeveloperCommand command);
}
