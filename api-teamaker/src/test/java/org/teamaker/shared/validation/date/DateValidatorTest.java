package org.teamaker.shared.validation.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {
    @Test
    public void testValidDate() {
        try {
            new MockValidatedClass(LocalDate.of(2000, 1, 1));
        } catch (Exception e) {
            fail("Should not throw any exception for a valid date");
        }
    }

    @Test
    public void testInvalidDate() {
        try {
            new MockValidatedClass(LocalDate.of(1800, 1, 1));
            fail("Should throw for an invalid date");
        } catch (Exception ignored) {
        }
    }
}
