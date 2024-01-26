package org.teamaker.developer.application.port.in.updateDeveloperInfo;

public interface UpdateDeveloperInfoUseCase {
    UpdateDeveloperInfoResponse.Response updateDeveloperInfo(UpdateDeveloperInfoCommand command);
}
