package org.teamaker.developer.application;

import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperResponse;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperUseCase;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

class ResignDeveloperService implements ResignDeveloperUseCase {
    private final LoadDeveloperPort loadDeveloperPort;
    private final SaveDeveloperPort saveDeveloperPort;
    private final SaveTeamPort saveTeamPort;

    public ResignDeveloperService(LoadDeveloperPort loadDeveloperPort, SaveDeveloperPort saveDeveloperPort, SaveTeamPort saveTeamPort) {
        this.loadDeveloperPort = loadDeveloperPort;
        this.saveDeveloperPort = saveDeveloperPort;
        this.saveTeamPort = saveTeamPort;
    }

    public ResignDeveloperResponse.Response resignDeveloper(ResignDeveloperCommand command) {
        try {
            Developer developerToResign = loadDeveloperPort.loadDeveloper(
                    new LoadDeveloperCommand(command.getDeveloperId())
            );
            List<Project> currentProjects = developerToResign.getCurrentProjects();
            List<String> errors = developerToResign.resign(currentProjects);
            if (errors != null) {
                return new ResignDeveloperResponse.MultipleErrorsResponse(errors);
            }
            for (Project project : currentProjects) {
                saveTeamPort.saveTeam(project.getTeam());
            }

            developerToResign = saveDeveloperPort.saveDeveloper(
                    new SaveDeveloperCommand(developerToResign)
            );

            return new ResignDeveloperResponse.SuccessResponse(developerToResign.getResignationDate());
        } catch (NoSuchElementException exception) {
            return new ResignDeveloperResponse.ErrorResponse(exception.getMessage());
        }
    }
}
