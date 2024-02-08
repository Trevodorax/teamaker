package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.project.domain.ProjectProgress;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamCommand;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamResponse;
import org.teamaker.team.application.port.in.removeDeveloperFromTeam.RemoveDeveloperFromTeamUseCase;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class RemoveDeveloperFromTeamService implements RemoveDeveloperFromTeamUseCase {
    private final LoadDeveloperPort loadDeveloperPort;
    private final LoadProjectPort loadProjectPort;
    private final LoadDeveloperProjectsPort loadDeveloperProjectsPort;
    private final SaveTeamPort saveTeamPort;
    private final LoadTeamPort loadTeamPort;

    public RemoveDeveloperFromTeamService(LoadDeveloperPort loadDeveloperPort, LoadProjectPort loadProjectPort, LoadDeveloperProjectsPort loadDeveloperProjectsPort, SaveTeamPort saveTeamPort, LoadTeamPort loadTeamPort) {
        this.loadDeveloperPort = loadDeveloperPort;
        this.loadProjectPort = loadProjectPort;
        this.loadDeveloperProjectsPort = loadDeveloperProjectsPort;
        this.saveTeamPort = saveTeamPort;
        this.loadTeamPort = loadTeamPort;
    }

    @Override
    public RemoveDeveloperFromTeamResponse.Response removeDeveloperFromTeam(RemoveDeveloperFromTeamCommand command) throws IllegalStateException {
        try {
            // load the dev and its projects
            Developer developer = loadDeveloperPort.loadDeveloper(
                    new LoadDeveloperCommand(command.getDeveloperId())
            );
            List<Project> developerProjects = loadDeveloperProjectsPort.loadDeveloperProjects(
                    new LoadDeveloperProjectsCommand(command.getDeveloperId())
            );
            developer.setProjectList(developerProjects);

            // check that the dev is in the project of this team
            if (developer.getProjectList().stream().noneMatch(obj -> obj.getProjectId().equals(command.getProjectId()))) {
                return new RemoveDeveloperFromTeamResponse.SingleErrorResponse("Developer isn't in this team.");
            }

            // load the project and the team
            Project project = loadProjectPort.loadProject(
                    new LoadProjectCommand(command.getProjectId())
            );
            Team team = loadTeamPort.loadTeam(
                    new LoadTeamCommand(command.getProjectId())
            );

            // check that the project hasn't started yet
            if (project.projectProgress() == ProjectProgress.IN_PROGRESS || project.projectProgress() == ProjectProgress.DONE) {
                return new RemoveDeveloperFromTeamResponse.SingleErrorResponse("Project has already started or is done");
            }

            // call the remove function
            List<String> errors = team.removeDeveloperById(command.getDeveloperId(), project, false);
            if (errors != null) {
                return new RemoveDeveloperFromTeamResponse.MultipleErrorsResponse(errors);
            }

            // save the new team
            Team updatedTeam = saveTeamPort.saveTeam(new SaveTeamCommand(team));

            return new RemoveDeveloperFromTeamResponse.SuccessResponse(
                    updatedTeam.getDevelopers()
                            .stream()
                            .map(Developer::toResponse)
                            .toList());
        } catch (NoSuchElementException e) {
            return new RemoveDeveloperFromTeamResponse
                    .SingleErrorResponse("Developer or project not found.");
        }
    }
}
