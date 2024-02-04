package org.teamaker.developer.adapter.out;

import org.junit.jupiter.api.Test;
import org.teamaker.developer.adapter.out.entity.DeveloperJPA;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperJPATest {

    @Test
    void toDomain() {
        DeveloperJPA developerJPA = new DeveloperJPA("id", "name", "email", LocalDate.now(), null, null, null, null);
        assertEquals("id", developerJPA.toDomain().getDeveloperId());
        assertEquals("name", developerJPA.toDomain().getFullName());
        assertEquals("email", developerJPA.toDomain().getEmail());
        assertEquals(LocalDate.now(), developerJPA.toDomain().getHiringDate());
    }
}