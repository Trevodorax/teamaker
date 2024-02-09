package org.teamaker.team.application;

import org.springframework.stereotype.Component;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperCommand;
import org.teamaker.developer.application.port.out.loadDeveloper.LoadDeveloperPort;
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
import org.teamaker.team.domain.TreatTeamStatus;

import java.util.List;

@Component
public class TreatTeamChangeRequestService implements TreatTeamChangeRequestUseCase {
    private final LoadTeamChangeRequestPort loadTeamChangeRequestPort;
    private final LoadProjectPort loadProjectPort;
    private final LoadDeveloperPort loadDeveloperPort;
    private final SaveTeamChangeRequestPort saveTeamChangeRequestPort;
    private final SaveTeamPort saveTeamPort;

    public TreatTeamChangeRequestService(LoadTeamChangeRequestPort loadTeamChangeRequestPort, LoadProjectPort loadProjectPort, LoadDeveloperPort loadDeveloperPort, SaveTeamChangeRequestPort saveTeamChangeRequestPort, SaveTeamPort saveTeamPort) {
        this.loadTeamChangeRequestPort = loadTeamChangeRequestPort;
        this.loadProjectPort = loadProjectPort;
        this.loadDeveloperPort = loadDeveloperPort;
        this.saveTeamChangeRequestPort = saveTeamChangeRequestPort;
        this.saveTeamPort = saveTeamPort;
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

        Project fromProject = loadProjectPort.loadProject(
                new LoadProjectCommand(teamChangeRequest.getFromProjectId())
        );
        Project toProject = loadProjectPort.loadProject(
                new LoadProjectCommand(teamChangeRequest.getToProjectId())
        );

        // try to modify
        List<String> errors = teamChangeRequest.treat(command.getStatus(), fromProject, toProject, developer);
        if(errors != null) {
            return new TreatTeamChangeRequestResponse.MultipleErrorsResponse(errors);
        }

        // save modified stuff
        saveTeamChangeRequestPort.saveTeamChangeRequest(new SaveTeamChangeRequestCommand(teamChangeRequest));
        saveTeamPort.saveTeam(new SaveTeamCommand(fromProject.getTeam()));
        saveTeamPort.saveTeam(new SaveTeamCommand(toProject.getTeam()));

        return new TreatTeamChangeRequestResponse.SuccessResponse(teamChangeRequest);
    }
}
