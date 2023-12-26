package org.teamaker.developer.application;

import java.time.LocalDate;

import org.teamaker.developer.application.port.in.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.ResignDeveloperUseCase;
import org.teamaker.developer.application.port.out.UpdateDeveloperPort;
import org.teamaker.developer.application.port.out.UpdateDeveloperResignationDateCommand;
import org.teamaker.developer.domain.Developer;

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
