package org.teamaker.developer.domain;

import java.time.LocalDate;

public class Developer {
    String fullName;
    String email;

    LocalDate hiringDate;

    public Developer(String fullName, String email, LocalDate hiringDate) {
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
    }
}
