package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperUseCase;
import org.teamaker.developer.application.port.out.updateDeveloper.UpdateDeveloperPort;
import org.teamaker.developer.application.port.out.updateDeveloper.UpdateDeveloperResignationDateCommand;

import java.time.LocalDate;

public class ResignDeveloperService implements ResignDeveloperUseCase {
    private final UpdateDeveloperPort updateDeveloperPort;

    public ResignDeveloperService(UpdateDeveloperPort updateDeveloperPort) {
        this.updateDeveloperPort = updateDeveloperPort;
    }

    public LocalDate resignDeveloper(ResignDeveloperCommand command) {
        LocalDate updatedResignationDate = updateDeveloperPort.resignDeveloper(new UpdateDeveloperResignationDateCommand(command.getEmail(), command.getResignationDate()));
        return updatedResignationDate;
    }
}
