package org.teamaker.developer.application.port.in.hireDeveloper;

public interface HireDeveloperUseCase {
    HireDeveloperResponse.Response hireDeveloper(HireDeveloperCommand command);
}
