package org.teamaker.developer.domain;

import java.time.LocalDate;

public record Developer(String fullName, String email, LocalDate hiringDate, LocalDate resignationDate) {
}