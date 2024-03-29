package org.teamaker.team.domain;

import lombok.Getter;
import org.teamaker.developer.domain.Developer;
import org.teamaker.project.domain.Project;
import org.teamaker.team.domain.dto.TeamChangeRequestResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TeamChangeRequest {
    private final String id;
    private final String developerId;
    private final String fromProjectId;
    private final String toProjectId;
    private TeamRequestStatus status;
    private final LocalDate submitDate;

    public TeamChangeRequest(String id, String developerId, String fromProjectId, String toProjectId, TeamRequestStatus status, LocalDate submitDate) {
        this.id = id;
        this.developerId = developerId;
        this.fromProjectId = fromProjectId;
        this.toProjectId = toProjectId;
        this.status = status;
        this.submitDate = submitDate;
    }

    public TeamChangeRequestResponse toResonse() {
        return new TeamChangeRequestResponse(id, developerId, fromProjectId, toProjectId, status, submitDate);
    }

    /**
     * Either accepts or refuses the team change request
     *
     * @param status The requested status
     * @param fromProject The project to move from
     * @param toProject The project to move to
     * @param developer The developer to move
     * @return The errors if there were any, otherwise null
     */
    public List<String> treat(TreatTeamStatus status, Project fromProject, Project toProject, Developer developer) {
        switch (status) {
            case TreatTeamStatus.DENIED -> {
                this.status = TeamRequestStatus.REFUSED;
            }
            case TreatTeamStatus.APPROVED -> {
                // check if switch is possible
                List<String> removalProblems = fromProject.removeDeveloperById(developerId, true);
                if(removalProblems != null) {
                    return removalProblems;
                }
                List<String> addingProblems = toProject.addDeveloper(developer, true);
                if(addingProblems != null) {
                    return addingProblems;
                }

                // execute switch
                fromProject.removeDeveloperById(developerId, false);
                toProject.addDeveloper(developer, false);

                this.status = TeamRequestStatus.ACCEPTED;
            }
        }

        return null;
    }
}
