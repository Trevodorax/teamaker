package org.teamaker.team.domain;

public class TeamChangeRequest {
    private final String id;
    private final String developerId;
    private final String requestedProjectId;

    public TeamChangeRequest(String id, String developerId, String requestedProjectId) {
        this.id = id;
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;
    }
}
