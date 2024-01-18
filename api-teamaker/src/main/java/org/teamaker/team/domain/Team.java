package org.teamaker.team.domain;

import org.teamaker.developer.domain.Developer;

import java.util.List;

public class Team {
    private final String projectId;
    private final List<Developer> developers;

    public Team(String projectId, List<Developer> developers) {
        this.projectId = projectId;
        this.developers = developers;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public String getProjectId() {
        return projectId;
    }
}
