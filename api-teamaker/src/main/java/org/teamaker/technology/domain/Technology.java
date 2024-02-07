package org.teamaker.technology.domain;

import lombok.Getter;
import org.teamaker.technology.domain.dto.TechnologyResponse;

@Getter
public class Technology {
    private final String technologyId;
    private final String name;

    public Technology(String technologyId, String name) {
        this.technologyId = technologyId;
        this.name = name;
    }

    public TechnologyResponse toResponse() {
        return new TechnologyResponse(this.technologyId, this.name);
    }
}
