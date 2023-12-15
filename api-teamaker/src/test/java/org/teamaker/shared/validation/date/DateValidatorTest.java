package org.teamaker.shared.validation.date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.teamaker.project.domain.Priority;
import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {
    @Test
    public void testValidDate() {
        try {
            new MockValidatedClass(LocalDate.of(2000, 1, 1));
        } catch (Exception e) {
            System.out.println("Error : " + e);
            fail("Should not throw any exception for a valid date");
        }
    }

    @Test
    public void testInvalidDate() {
        try {
            new MockValidatedClass(LocalDate.of(1800, 1, 1));
            fail("Should throw for an invalid date");
        } catch (Exception e) {
        }
    }
}
