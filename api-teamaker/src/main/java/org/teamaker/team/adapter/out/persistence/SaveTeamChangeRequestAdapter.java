package org.teamaker.team.adapter.out.persistence;

import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestCommand;
import org.teamaker.team.application.port.out.saveTeamChangeRequest.SaveTeamChangeRequestPort;
import org.teamaker.team.domain.TeamChangeRequest;

public class SaveTeamChangeRequestAdapter implements SaveTeamChangeRequestPort {
    @Override
    public TeamChangeRequest saveTeamChangeRequest(SaveTeamChangeRequestCommand command) {
        return null;
    }
}
