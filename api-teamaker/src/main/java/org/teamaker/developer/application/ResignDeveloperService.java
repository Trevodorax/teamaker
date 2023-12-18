package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.ResignDeveloperUseCase;
import org.teamaker.developer.application.port.out.UpdateDeveloperPort;
import org.teamaker.developer.application.port.out.UpdateDeveloperResignationDateCommand;
import org.teamaker.developer.domain.Developer;

import java.util.Date;

public class ResignDeveloperService implements ResignDeveloperUseCase {
    private final UpdateDeveloperPort updateDeveloperPort;

    public ResignDeveloperService(UpdateDeveloperPort updateDeveloperPort) {
        this.updateDeveloperPort = updateDeveloperPort;
    }

    public Date resignDeveloper(ResignDeveloperCommand command) {
        Developer updatedDeveloper = updateDeveloperPort.resignDeveloper(new UpdateDeveloperResignationDateCommand(command.getEmail(), command.getResignationDate()));
        return updatedDeveloper.getResignationDate();
    }
}
