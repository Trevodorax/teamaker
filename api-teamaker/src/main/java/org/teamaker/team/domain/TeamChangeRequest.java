package org.teamaker.team.domain;

public class TeamChangeRequest {
    private final String id;
    private final String developerId;
    private final String requestedProjectId;
    private final TeamRequestStatus status;

    public TeamChangeRequest(String id, String developerId, String requestedProjectId, TeamRequestStatus status) {
        this.id = id;
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;
        this.status = status;
    }
}
