package org.teamaker.team.application.port.out.saveTeamChangeRequest;

import org.teamaker.team.domain.TeamChangeRequest;

public interface SaveTeamChangeRequestPort {
    TeamChangeRequest saveTeamChangeRequest(SaveTeamChangeRequestCommand command);
}
