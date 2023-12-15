package org.teamaker.domain;

public class Project {
    private final String projectId;

    public Project(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return this.projectId;
    }
}
