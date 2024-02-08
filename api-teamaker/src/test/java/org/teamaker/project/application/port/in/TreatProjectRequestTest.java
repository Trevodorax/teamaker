package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.treatProject.TreatProjectRequest;
import org.teamaker.project.domain.ProjectStatus;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class TreatProjectRequestTest {
    private static ProjectStatus validStatus;

    @BeforeAll
    public static void setUp() {
        validStatus = ProjectStatus.ACCEPTED;
    }

    @Test
    public void testConstructorValidData() {
        TreatProjectRequest request = new TreatProjectRequest(validStatus);
        assertEquals(validStatus, request.getStatus());
    }

    @Test
    public void testConstructorEmptyStatus() {
        assertThrows(ConstraintViolationException.class,
                () -> new TreatProjectRequest(null));
    }

}