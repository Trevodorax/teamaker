package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.updateProjectInfo.UpdateProjectInfoRequest;
import org.teamaker.project.domain.ProjectPriority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UpdateProjectInfoRequestTest {
    private static String validName;
    private static String validDescription;
    private static ProjectPriority validPriority;

    @BeforeAll
    public static void setUp() {
        validName = "Project Name";
        validDescription = "Project Description";
        validPriority = ProjectPriority.CRITICAL;
    }

    @Test
    public void testConstructorValidData() {
        UpdateProjectInfoRequest request = new UpdateProjectInfoRequest(validName, validDescription, validPriority);
        assertEquals(validName, request.getName());
        assertEquals(validDescription, request.getDescription());
    }

    @Test
    public void testConstructorEmptyName() {
        UpdateProjectInfoRequest request = new UpdateProjectInfoRequest(null, validDescription, validPriority);
        assertNull(request.getName());
        assertEquals(validDescription, request.getDescription());
    }

    @Test
    public void testConstructorEmptyDescription() {
        UpdateProjectInfoRequest request = new UpdateProjectInfoRequest(validName, null, validPriority);
        assertEquals(validName, request.getName());
        assertNull(request.getDescription());
    }

    @Test
    public void testConstructorEmptyNameAndDescription() {
        UpdateProjectInfoRequest request = new UpdateProjectInfoRequest(null, null, validPriority);
        assertNull(request.getName());
        assertNull(request.getDescription());
    }
}