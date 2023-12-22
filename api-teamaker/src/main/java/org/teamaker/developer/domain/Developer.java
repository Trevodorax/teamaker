package org.teamaker.developer.domain;

import java.time.LocalDate;

public class Developer {
    String fullName;
    String email;

    LocalDate hiringDate;
    LocalDate resignationDate;

    public Developer(String fullName, String email, LocalDate hiringDate, LocalDate resignationDate) {
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
        this.resignationDate = resignationDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }
}
