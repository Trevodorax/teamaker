package org.teamaker.developer.domain;

import org.teamaker.adapter.in.HireDeveloperUseCase;
import org.teamaker.adapter.out.persistence.CreateDeveloperPort;

public class HireDeveloperService implements HireDeveloperUseCase {
    private final CreateDeveloperPort createDeveloperPort;

    public HireDeveloperService(CreateDeveloperPort createDeveloperPort) {
        this.createDeveloperPort = createDeveloperPort;
    }

    public boolean hireDeveloper(String fullName, String email) {
        Developer developer = new Developer(fullName, email);
        return createDeveloperPort.createDeveloper(developer);
    }
}
