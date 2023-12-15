package org.teamaker.shared.validation.date;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

class MockValidatedClass extends SelfValidating<MockValidatedClass> {
    @IsAfter(current = "1900-01-01")
    private final LocalDate testDate;

    public MockValidatedClass(LocalDate testDate) {
        this.testDate = testDate;

        this.validateSelf();
    }

}