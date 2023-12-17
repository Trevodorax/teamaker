package org.teamaker.developer.domain;

import java.util.Date;

public class Developer {
    String fullName;
    String email;

    Date hiringDate;
    Date resignationDate;

    public Developer(String fullName, String email, Date hiringDate, Date resignationDate) {
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
        this.resignationDate = resignationDate;
    }

    public Date getResignationDate() {
        return resignationDate;
    }
}
