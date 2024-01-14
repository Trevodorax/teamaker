package org.teamaker.team.domain;

public class TeamChangeRequest {
    private final String developerId;
    private final String requestedProjectId;

    public TeamChangeRequest(String developerId, String requestedProjectId) {
        this.developerId = developerId;
        this.requestedProjectId = requestedProjectId;
    }
}
