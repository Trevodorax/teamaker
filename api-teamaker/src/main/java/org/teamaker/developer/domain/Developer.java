package org.teamaker.developer.domain;

import org.teamaker.developer.domain.dto.DeveloperResponse;

import java.time.LocalDate;

public class Developer {
    private final String developerId;
    private String fullName;
    private String email;

    private LocalDate hiringDate;
    private LocalDate resignationDate;

    public Developer(String developerId, String fullName, String email, LocalDate hiringDate) {
        this.developerId = developerId;
        this.fullName = fullName;
        this.email = email;
        this.hiringDate = hiringDate;
    }

    public void resign(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public DeveloperStatus getStatus() {
        if (this.resignationDate == null || this.resignationDate.isAfter(LocalDate.now())) {
            return DeveloperStatus.ACTIVE;
        }
        return DeveloperStatus.RESIGNED;
    }

    public DeveloperResponse toResponse() {
        return new DeveloperResponse(this.developerId, this.fullName, this.email, this.hiringDate, this.resignationDate, this.getStatus());
    }
}
