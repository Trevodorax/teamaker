package org.teamaker.developer.domain;

import java.util.Date;

public class Developer {
    String fullName;
    String email;

    Date hiringDate;

    public Developer(String fullName, String email, Date hiringDate) {
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
    }
}
