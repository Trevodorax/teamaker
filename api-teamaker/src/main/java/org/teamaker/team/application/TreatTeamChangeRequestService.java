package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsCommand;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsPort;
import org.teamaker.developer.application.port.out.loadDeveloperSkills.LoadDeveloperSkillsResponse;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.application.port.out.loadProject.LoadProjectCommand;
import org.teamaker.project.application.port.out.loadProject.LoadProjectPort;
import org.teamaker.project.domain.Project;
import org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase.TreatTeamChangeRequestCommand;
import org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase.TreatTeamChangeRequestResponse;
import org.teamaker.team.application.port.in.treatTeamChangeRequestUseCase.TreatTeamChangeRequestUseCase;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.loadTeamChangeRequest.LoadTeamChangeRequestPort;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamCommand;
import org.teamaker.team.application.port.out.saveTeam.SaveTeamPort;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;
import org.teamaker.team.domain.TeamRequestStatus;
import org.teamaker.technology.domain.Technology;

import java.util.List;

@Component
public class TreatTeamChangeRequestService implements TreatTeamChangeRequestUseCase {
    private final LoadTeamChangeRequestPort loadTeamChangeRequestPort;
    private final LoadProjectPort loadProjectPort;
    private final LoadDeveloperPort loadDeveloperPort;
    private final SaveTeamChangeRequestPort saveTeamChangeRequestPort;
    private final SaveTeamPort saveTeamPort;
    private final LoadDeveloperSkillsPort loadDeveloperSkillsPort;

    public TreatTeamChangeRequestService(LoadTeamChangeRequestPort loadTeamChangeRequestPort, LoadProjectPort loadProjectPort, LoadDeveloperPort loadDeveloperPort, SaveTeamChangeRequestPort saveTeamChangeRequestPort, SaveTeamPort saveTeamPort, LoadDeveloperSkillsPort loadDeveloperSkillsPort) {
        this.loadTeamChangeRequestPort = loadTeamChangeRequestPort;
        this.loadProjectPort = loadProjectPort;
        this.loadDeveloperPort = loadDeveloperPort;
        this.saveTeamChangeRequestPort = saveTeamChangeRequestPort;
        this.saveTeamPort = saveTeamPort;
        this.loadDeveloperSkillsPort = loadDeveloperSkillsPort;
    }

    @Override
    public TreatTeamChangeRequestResponse.Response treatTeamChangeRequest(TreatTeamChangeRequestCommand command) {

        // load everything
        TeamChangeRequest teamChangeRequest = loadTeamChangeRequestPort.loadTeamChangeRequest(
                new LoadTeamChangeRequestCommand(command.getTeamChangeRequestId())
        );
        if (teamChangeRequest.getStatus() != TeamRequestStatus.PENDING) {
            return new TreatTeamChangeRequestResponse
                    .SingleErrorResponse("Team change request is not pending");
        }

        Developer developer = loadDeveloperPort.loadDeveloper(
                new LoadDeveloperCommand(teamChangeRequest.getDeveloperId())
        );

        List<LoadDeveloperSkillsResponse> skills = loadDeveloperSkillsPort
                .loadDeveloperSkills(
                        new LoadDeveloperSkillsCommand(developer.getDeveloperId())
                );

        List<Technology> technologyList = skills.stream()
                .map(skillResponse -> new Technology(skillResponse.technology().technologyId(), skillResponse.technology().name()))
                .toList();
        developer.setSkills(technologyList);


        Project fromProject = loadProjectPort.loadProject(
                new LoadProjectCommand(teamChangeRequest.getFromProjectId())
        );

        for (Developer dev : fromProject.getTeam().getDevelopers()) {
            List<LoadDeveloperSkillsResponse> skillsDevFromProject = loadDeveloperSkillsPort
                    .loadDeveloperSkills(
                            new LoadDeveloperSkillsCommand(dev.getDeveloperId())
                    );

            List<Technology> technologyListDevFromProject = skills.stream()
                    .map(skillResponse -> new Technology(skillResponse.technology().technologyId(), skillResponse.technology().name()))
                    .toList();
            dev.setSkills(technologyList);
        }

        Project toProject = loadProjectPort.loadProject(
                new LoadProjectCommand(teamChangeRequest.getToProjectId())
        );

        for (Developer dev : toProject.getTeam().getDevelopers()) {
            List<LoadDeveloperSkillsResponse> skillsDevtoProjectt = loadDeveloperSkillsPort
                    .loadDeveloperSkills(
                            new LoadDeveloperSkillsCommand(dev.getDeveloperId())
                    );

            List<Technology> technologyListDevtoProject = skills.stream()
                    .map(skillResponse -> new Technology(skillResponse.technology().technologyId(), skillResponse.technology().name()))
                    .toList();
            dev.setSkills(technologyList);
        }

        // try to modify
        List<String> errors = teamChangeRequest.treat(command.getStatus(), fromProject, toProject, developer);
        if (errors != null) {
            return new TreatTeamChangeRequestResponse.MultipleErrorsResponse(errors);
        }

        // save modified stuff
        saveTeamChangeRequestPort.saveTeamChangeRequest(new SaveTeamChangeRequestCommand(teamChangeRequest));
        saveTeamPort.saveTeam(new SaveTeamCommand(fromProject.getTeam()));
        saveTeamPort.saveTeam(new SaveTeamCommand(toProject.getTeam()));

        return new TreatTeamChangeRequestResponse.SuccessResponse(teamChangeRequest);
    }
}
