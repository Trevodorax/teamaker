package org.teamaker.developer.application.port.in.getDeveloper;

public interface GetDeveloperUseCase {
    GetDeveloperResponse.Response getDeveloper(GetDeveloperCommand command);
}
