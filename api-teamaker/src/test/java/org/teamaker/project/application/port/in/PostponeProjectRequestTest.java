package org.teamaker.project.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.application.port.in.postponeProject.PostponeProjectRequest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PostponeProjectRequestTest {
    private static LocalDate validStartDate;
    private static LocalDate validEndDate;

    @BeforeAll
    public static void setUp() {
        validStartDate = LocalDate.now();
        validEndDate = LocalDate.now().plusDays(1);
    }

    @Test
    public void testConstructorValidData() {
        PostponeProjectRequest request = new PostponeProjectRequest(validStartDate, validEndDate);
        assertEquals(validStartDate, request.getNewStartDate());
        assertEquals(validEndDate, request.getNewEndDate());
    }

    @Test
    public void testConstructorEmptyStartDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new PostponeProjectRequest(null, validEndDate));
    }

    @Test
    public void testConstructorEmptyEndDate() {
        PostponeProjectRequest request = new PostponeProjectRequest(validStartDate, null);
        assertEquals(validStartDate, request.getNewStartDate());
        assertNull(request.getNewEndDate());
    }

    @Test
    public void testConstructorEmptyStartDateAndEndDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new PostponeProjectRequest(null, null));
    }

    @Test
    public void testConstructorEndDateBeforeStartDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new PostponeProjectRequest(validEndDate, validStartDate));
    }

}