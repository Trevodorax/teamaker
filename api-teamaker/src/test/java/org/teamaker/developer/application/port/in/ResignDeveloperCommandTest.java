package org.teamaker.developer.application.port.in;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.developer.application.port.in.resignDeveloper.ResignDeveloperCommand;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResignDeveloperCommandTest {
    private static String validDeveloperId;
    private static LocalDate validResignationDate;

    @BeforeAll
    static void setUp() {
        validDeveloperId = "validDeveloperId";
        validResignationDate = LocalDate.now().plusDays(3);
    }

    @Test
    void testConstructor() {
        ResignDeveloperCommand resignDeveloperCommand = new ResignDeveloperCommand(validDeveloperId, validResignationDate);
        assertEquals(validDeveloperId, resignDeveloperCommand.getDeveloperId());
    }

    @Test
    void testConstructorNullDeveloperId() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(null, validResignationDate));
    }

    @Test
    void testConstructorNullResignationDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(validDeveloperId, null));
    }

    @Test
    void testConstructorPastResignationDate() {
        assertThrows(ConstraintViolationException.class,
                () -> new ResignDeveloperCommand(validDeveloperId, LocalDate.now().minusDays(1)));
    }
}