package org.teamaker.team.application;

import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperCommand;
import org.teamaker.developer.application.port.out.saveDeveloper.SaveDeveloperPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.developer.domain.dto.DeveloperResponse;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamCommand;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamUseCase;

import java.util.List;

public class AssignDeveloperToTeamService implements AssignDeveloperToTeamUseCase {
    private final LoadDeveloperPort loadDeveloperPort;
    private final LoadProjectPort loadProjectPort;
    private final LoadDeveloperProjectsPort loadDeveloperProjectsPort;
    private final SaveDeveloperPort saveDeveloperPort;

    public AssignDeveloperToTeamService(LoadDeveloperPort loadDeveloperPort, LoadProjectPort loadProjectPort, LoadDeveloperProjectsPort loadDeveloperProjectsPort, SaveDeveloperPort saveDeveloperPort) {
        this.loadDeveloperPort = loadDeveloperPort;
        this.loadProjectPort = loadProjectPort;
        this.loadDeveloperProjectsPort = loadDeveloperProjectsPort;
        this.saveDeveloperPort = saveDeveloperPort;
    }

    @Override
    public DeveloperResponse assignDeveloperToTeam(AssignDeveloperToTeamCommand command) throws IllegalArgumentException {
        // load developer and project infos
        Developer developer = loadDeveloperPort.loadDeveloper(
                new LoadDeveloperCommand(command.getDeveloperId())
        );

        List<Project> developerProjects = loadDeveloperProjectsPort.loadDeveloperProjects(
                new LoadDeveloperProjectsCommand(command.getDeveloperId())
        );

        developer.setProjectList(developerProjects);

        Project project = loadProjectPort.loadProject(
                new LoadProjectCommand(command.getProjectId())
        );

        // check if the dev is available
        boolean isAvailable = developer.checkAvailability(project);

        if(!isAvailable) {
            throw new IllegalArgumentException("Developer is not available for this project.");
        }

        // FIXME: add the dev to the team and save team, instead of adding project to list of developer

        // assign the project to the developer and save the changes
        developer.addProject(project);

        Developer savedDeveloper = saveDeveloperPort.saveDeveloper(
                new SaveDeveloperCommand(developer)
        );

        return savedDeveloper.toResponse();
    }
}
