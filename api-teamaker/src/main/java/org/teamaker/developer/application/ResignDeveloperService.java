package org.teamaker.developer.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperResponse;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperUseCase;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;

import java.util.List;
import java.util.NoSuchElementException;

@Component
class ResignDeveloperService implements ResignDeveloperUseCase {
    private final LoadDeveloperPort loadDeveloperPort;
    private final LoadDeveloperProjectsPort loadDeveloperProjectsPort;
    private final SaveDeveloperPort saveDeveloperPort;
    private final SaveTeamPort saveTeamPort;

    public ResignDeveloperService(LoadDeveloperPort loadDeveloperPort, LoadDeveloperProjectsPort loadDeveloperProjectsPort, SaveDeveloperPort saveDeveloperPort, SaveTeamPort saveTeamPort) {
        this.loadDeveloperPort = loadDeveloperPort;
        this.loadDeveloperProjectsPort = loadDeveloperProjectsPort;
        this.saveDeveloperPort = saveDeveloperPort;
        this.saveTeamPort = saveTeamPort;
    }

    public ResignDeveloperResponse.Response resignDeveloper(ResignDeveloperCommand command) {
        try {
            Developer developerToResign = loadDeveloperPort.loadDeveloper(
                    new LoadDeveloperCommand(command.getDeveloperId())
            );
            if (developerToResign.getResignationDate() != null) {
                return new ResignDeveloperResponse.ErrorResponse("Developer is already resigned");
            }
            List<Project> developerProjects = loadDeveloperProjectsPort.loadDeveloperProjects(
                    new LoadDeveloperProjectsCommand(developerToResign.getDeveloperId())
            );
            developerToResign.setProjectList(developerProjects);
            List<Project> currentProjects = developerToResign.getCurrentProjects();
            if (currentProjects == null) {
                return new ResignDeveloperResponse.ErrorResponse("Developer is not assigned to any project");
            }
            List<String> errors = developerToResign.resign(currentProjects);
            if (errors != null) {
                return new ResignDeveloperResponse.MultipleErrorsResponse(errors);
            }
            for (Project project : currentProjects) {
                saveTeamPort.saveTeam(new SaveTeamCommand(project.getTeam()));
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
