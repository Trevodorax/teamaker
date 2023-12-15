package org.teamaker.application;

import org.springframework.stereotype.Component;
import org.teamaker.application.port.in.HireDeveloperUseCase;
import org.teamaker.adapter.out.persistence.CreateDeveloperPort;
import org.teamaker.developer.domain.Developer;

@Component
class HireDeveloperService implements HireDeveloperUseCase {
    private final CreateDeveloperPort createDeveloperPort;

    public HireDeveloperService(CreateDeveloperPort createDeveloperPort) {
        this.createDeveloperPort = createDeveloperPort;
    }

    public Developer hireDeveloper(String fullName, String email) {
        return createDeveloperPort.createDeveloper(fullName, email);
    }
}
