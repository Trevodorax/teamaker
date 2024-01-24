package org.teamaker.technology.domain;

import org.teamaker.technology.domain.dto.TechnologyResponse;

public class Technology {
    private final String technologyId;
    private final String name;

    public Technology(String technologyId, String name) {
        this.technologyId = technologyId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTechnologyId() {
        return technologyId;
    }

    public TechnologyResponse toResponse() {
        return new TechnologyResponse(this.technologyId, this.name);
    }
}
