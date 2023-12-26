package org.teamaker.developer.application.port.in;

import java.time.LocalDate;

public interface ResignDeveloperUseCase {
    public LocalDate resignDeveloper(ResignDeveloperCommand command);
}
