package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperProjects.LoadDeveloperProjectsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamCommand;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamResponse;
import org.teamaker.team.application.port.in.assignDeveloperToTeam.AssignDeveloperToTeamUseCase;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamCommand;
import org.teamaker.team.application.port.out.loadTeam.LoadTeamPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.domain.Team;
import org.teamaker.technology.domain.Technology;
import org.teamaker.technology.domain.dto.TechnologyResponse;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class AssignDeveloperToTeamService implements AssignDeveloperToTeamUseCase {
    private final LoadDeveloperPort loadDeveloperPort;
    private final LoadProjectPort loadProjectPort;
    private final LoadDeveloperProjectsPort loadDeveloperProjectsPort;
    private final LoadTeamPort loadTeamPort;
    private final SaveTeamPort saveTeamPort;
    private final LoadDeveloperSkillsPort loadDeveloperSkillsPort;

    public AssignDeveloperToTeamService(LoadDeveloperPort loadDeveloperPort, LoadProjectPort loadProjectPort, LoadDeveloperProjectsPort loadDeveloperProjectsPort, LoadTeamPort loadTeamPort, SaveTeamPort saveTeamPort, LoadDeveloperSkillsPort loadDeveloperSkillsPort) {
        this.loadDeveloperPort = loadDeveloperPort;
        this.loadProjectPort = loadProjectPort;
        this.loadDeveloperProjectsPort = loadDeveloperProjectsPort;
        this.loadTeamPort = loadTeamPort;
        this.saveTeamPort = saveTeamPort;
        this.loadDeveloperSkillsPort = loadDeveloperSkillsPort;
    }

    @Override
    public AssignDeveloperToTeamResponse.Response assignDeveloperToTeam(AssignDeveloperToTeamCommand command) throws NoSuchElementException {
        try {
            // load developer and project infos
            Developer developer = loadDeveloperPort.loadDeveloper(
                    new LoadDeveloperCommand(command.getDeveloperId())
            );

            List<Project> developerProjects = loadDeveloperProjectsPort.loadDeveloperProjects(
                    new LoadDeveloperProjectsCommand(command.getDeveloperId())
            );

            developer.setProjectList(developerProjects);

            List<LoadDeveloperSkillsResponse> developerSkillsResponse = loadDeveloperSkillsPort.loadDeveloperSkills(
                    new LoadDeveloperSkillsCommand(command.getDeveloperId())
            );
            List<Technology> developerSkills = developerSkillsResponse.stream().map(skill -> {
                        TechnologyResponse technology = skill.technology();
                        return new Technology(technology.technologyId(), technology.name());
                    }
            ).toList();

            developer.setSkills(developerSkills);

            Project project = loadProjectPort.loadProject(
                    new LoadProjectCommand(command.getProjectId())
            );

            // check if the dev is available
            boolean isAvailable = developer.checkAvailability(project);

            if (!isAvailable) {
                return new AssignDeveloperToTeamResponse.SingleErrorResponse("Developer is not available for this project.");
            }

            // add the dev to the team and save team
            Team team = loadTeamPort.loadTeam(
                    new LoadTeamCommand(command.getProjectId())
            );

            List<String> addDeveloperErrors = team.addDeveloper(developer, project, false);
            if (addDeveloperErrors != null) {
                return new AssignDeveloperToTeamResponse.MultipleErrorsResponse(addDeveloperErrors);
            }

            Team updatedTeam = saveTeamPort.saveTeam(new SaveTeamCommand(team));

            return new AssignDeveloperToTeamResponse.SuccessResponse(
                    updatedTeam.getDevelopers()
                            .stream()
                            .map(Developer::toResponse)
                            .toList());
        } catch (NoSuchElementException e) {
            return new AssignDeveloperToTeamResponse.SingleErrorResponse("Developer or project not found.");
        } catch (IllegalArgumentException e) {
            return new AssignDeveloperToTeamResponse.SingleErrorResponse(e.getMessage());
        }
    }
}
