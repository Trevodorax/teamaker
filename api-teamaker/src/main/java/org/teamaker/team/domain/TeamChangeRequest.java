package org.teamaker.team.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class TeamChangeRequest {
    private final String id;
    private final String developerId;
    private final String requestedProjectId;
    private final TeamRequestStatus status;
    private final LocalDate submitDate;

    // for db
    public TeamChangeRequest(String id, String developerId, String requestedProjectId, TeamRequestStatus status, LocalDate submitDate) {
        this.id = id;
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;
        this.status = status;
        this.submitDate = submitDate;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }
}
