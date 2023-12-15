package org.teamaker.application.port.in;

import org.teamaker.developer.domain.Developer;

public interface HireDeveloperUseCase {
    public Developer hireDeveloper(String fullName, String email);
}
