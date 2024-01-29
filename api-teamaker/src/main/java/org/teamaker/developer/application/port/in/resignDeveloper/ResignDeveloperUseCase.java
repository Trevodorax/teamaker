package org.teamaker.developer.application.port.in.resignDeveloper;

public interface ResignDeveloperUseCase {
    ResignDeveloperResponse.Response resignDeveloper(ResignDeveloperCommand command);
}
