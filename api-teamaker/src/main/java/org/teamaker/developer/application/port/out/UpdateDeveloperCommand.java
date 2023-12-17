package org.teamaker.developer.application.port.out;

import org.teamaker.shared.validation.SelfValidating;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateDeveloperCommand extends SelfValidating<UpdateDeveloperCommand> {
    private final String fullName;
    @NotNull
    private final String email;
    private final Date hiringDate;
    private final Date resignationDate;

    public UpdateDeveloperCommand(String fullName, String email, Date hiringDate, Date resignationDate) {
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
        this.resignationDate = resignationDate;

        if (fullName == null && hiringDate == null && resignationDate == null) {
            throw new IllegalArgumentException("At least one property has to not be null.");
        }

        this.validateSelf();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public Date getResignationDate() {
        return resignationDate;
    }
}
