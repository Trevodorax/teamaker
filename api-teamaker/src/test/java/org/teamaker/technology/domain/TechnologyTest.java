package org.teamaker.technology.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.technology.domain.dto.TechnologyResponse;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyTest {
    private static Technology technology;

    @BeforeAll
    public static void setUp() {
        technology = new Technology("technologyId", "name");
    }

    @Test
    public void toResponse() {
        TechnologyResponse response = technology.toResponse();
        assertEquals(technology.getTechnologyId(), response.technologyId());
        assertEquals(technology.getName(), response.name());
    }

}